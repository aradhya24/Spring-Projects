package com.aurionpro.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aurionpro.dto.TransactionCreateRequest;
import com.aurionpro.dto.TransactionResponse;
import com.aurionpro.entity.Account;
import com.aurionpro.entity.Customer;
import com.aurionpro.entity.Transaction;
import com.aurionpro.repository.AccountRepo;
import com.aurionpro.repository.CustomerRepo;
import com.aurionpro.repository.TransactionRepo;
import com.aurionpro.service.EmailService;
import com.aurionpro.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final CustomerRepo customerRepo;
    private final AccountRepo accountRepo;
    private final EmailService emailService;
    private final ModelMapper modelMapper;

    // ----------------------- CREATE TRANSACTION -----------------------
    @Override
    public TransactionResponse createTransaction(TransactionCreateRequest req) {
        Customer customer = getAuthorizedCustomer(req.getCustomerId());

        if ("TRANSFER".equalsIgnoreCase(req.getTransType())) {
            return handleTransfer(req, customer);
        }
        return handleSimpleTransaction(req, customer);
    }

    private TransactionResponse handleSimpleTransaction(TransactionCreateRequest req, Customer customer) {
        Account acc = getCustomerAccount(req.getFromAccountId(), customer);

        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(req.getTransType())
                ? acc.getBalance().subtract(req.getAmount())
                : acc.getBalance().add(req.getAmount());

        if ("DEBIT".equalsIgnoreCase(req.getTransType()) && newBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Insufficient balance");

        acc.setBalance(newBalance);
        accountRepo.save(acc);

        Transaction txn = Transaction.builder()
                .account(acc)
                .customer(customer)
                .amount(req.getAmount())
                .transType(req.getTransType())
                .remarks(req.getRemarks())
                .date(LocalDateTime.now())
                .balanceAfterTxn(newBalance)
                .toAccountId(null)
                .build();

        transactionRepo.save(txn);

        // Send email
        emailService.sendTransactionAlertEmail(customer.getEmailid(),
                acc.getAccountId(),
                txn.getTransType(),
                txn.getRemarks(),
                txn.getAmount().toString());

        return mapToResponse(txn, acc.getAccountId(), customer.getId());
    }

    private TransactionResponse handleTransfer(TransactionCreateRequest req, Customer sender) {
        Account fromAcc = getCustomerAccount(req.getFromAccountId(), sender);
        Account toAcc = getAuthorizedAccount(req.getToAccountId());

        if (!fromAcc.getIsAccountActive() || !toAcc.getIsAccountActive())
            throw new RuntimeException("One of the accounts is inactive");

        if (!fromAcc.getCustomer().getIsCustomerActive() || !toAcc.getCustomer().getIsCustomerActive())
            throw new RuntimeException("One of the account owners is inactive");

        if (fromAcc.getBalance().compareTo(req.getAmount()) < 0)
            throw new RuntimeException("Insufficient balance");

        fromAcc.setBalance(fromAcc.getBalance().subtract(req.getAmount()));
        toAcc.setBalance(toAcc.getBalance().add(req.getAmount()));
        accountRepo.save(fromAcc);
        accountRepo.save(toAcc);

        Transaction debit = Transaction.builder()
                .account(fromAcc)
                .customer(sender)
                .amount(req.getAmount())
                .transType("DEBIT")
                .remarks("Transfer to Account " + toAcc.getAccountId())
                .date(LocalDateTime.now())
                .balanceAfterTxn(fromAcc.getBalance())
                .toAccountId(toAcc.getAccountId())
                .build();

        Transaction credit = Transaction.builder()
                .account(toAcc)
                .customer(toAcc.getCustomer())
                .amount(req.getAmount())
                .transType("CREDIT")
                .remarks("Transfer from Account " + fromAcc.getAccountId())
                .date(LocalDateTime.now())
                .balanceAfterTxn(toAcc.getBalance())
                .toAccountId(fromAcc.getAccountId())
                .build();

        transactionRepo.save(debit);
        transactionRepo.save(credit);

        // Emails
        emailService.sendTransactionAlertEmail(sender.getEmailid(),
                fromAcc.getAccountId(),
                "TRANSFER DEBIT",
                debit.getRemarks(),
                debit.getAmount().toString());

        emailService.sendTransactionAlertEmail(toAcc.getCustomer().getEmailid(),
                toAcc.getAccountId(),
                "TRANSFER CREDIT",
                credit.getRemarks(),
                credit.getAmount().toString());

        return mapToResponse(debit, fromAcc.getAccountId(), sender.getId());
    }

    // ----------------------- GET PASSBOOK -----------------------
    @Override
    public List<TransactionResponse> getPassbook(Long accountId) {
        Account acc = getAuthorizedAccount(accountId);

        List<TransactionResponse> passbook = acc.getTransactions().stream()
                .sorted((t1, t2) -> t1.getDate().compareTo(t2.getDate()))
                .map(t -> mapToResponse(t, accountId, acc.getCustomer().getId()))
                .collect(Collectors.toList());

        // Send PDF email
        emailService.sendPassbookPdfEmail(acc.getCustomer().getEmailid(), accountId, passbook);

        return passbook;
    }


    @Override
    public List<TransactionResponse> getTransactionsByAccountId(Long accountId) {
        Account acc = getAuthorizedAccount(accountId);

        return acc.getTransactions().stream()
                .map(t -> mapToResponse(t, accountId, acc.getCustomer().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getTransactionsByCustomerId(Long customerId) {
        Customer cust = getAuthorizedCustomer(customerId);

        return cust.getTransactions().stream()
                .map(t -> mapToResponse(t, t.getAccount().getAccountId(), customerId))
                .collect(Collectors.toList());
    }

    // ----------------------- HELPER METHODS -----------------------
    private Customer getAuthorizedCustomer(Long customerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .anyMatch(role -> role.equals("ROLE_ADMIN") || role.equals("ROLE_SUPER_ADMIN"));

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (!isAdmin) {
            Customer currentCustomer = customerRepo.findByUserUsername(auth.getName())
                    .orElseThrow(() -> new RuntimeException("Logged-in customer not found"));

            if (!currentCustomer.getId().equals(customerId))
                throw new AccessDeniedException("Unauthorized");

            if (!currentCustomer.getIsCustomerActive())
                throw new RuntimeException("Inactive customer cannot perform transactions");
        }

        if (!customer.getIsCustomerActive())
            throw new RuntimeException("Cannot perform transaction: Customer is inactive");

        return customer;
    }

    private Account getCustomerAccount(Long accountId, Customer customer) {
        Account acc = getAuthorizedAccount(accountId);

        if (!acc.getCustomer().getId().equals(customer.getId()))
            throw new AccessDeniedException("This account does not belong to the customer");

        return acc;
    }

    private Account getAuthorizedAccount(Long accountId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .anyMatch(role -> role.equals("ROLE_ADMIN") || role.equals("ROLE_SUPER_ADMIN"));


        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

//        if (!acc.getIsAccountActive())
//            throw new RuntimeException("Cannot access: Account is inactive");

        Customer accountOwner = acc.getCustomer();
        if (!accountOwner.getIsCustomerActive())
            throw new RuntimeException("Cannot access: Account owner is inactive");

        if (!isAdmin) {
            Customer currentCustomer = customerRepo.findByUserUsername(auth.getName())
                    .orElseThrow(() -> new RuntimeException("Logged-in customer not found"));

            if (!acc.getCustomer().getId().equals(currentCustomer.getId()))
                throw new AccessDeniedException("Unauthorized");

            if (!currentCustomer.getIsCustomerActive())
                throw new RuntimeException("Your account is inactive. Please contact admin.");
            
            if (!acc.getIsAccountActive())
                throw new RuntimeException("Cannot access: Account is inactive");
        }

        return acc;
    }

    private TransactionResponse mapToResponse(Transaction txn, Long accountId, Long customerId) {
        TransactionResponse dto = modelMapper.map(txn, TransactionResponse.class);
        dto.setAccountId(accountId);
        dto.setCustomerId(customerId);
        dto.setBalanceAfterTxn(txn.getBalanceAfterTxn());
        dto.setToAccountId(txn.getToAccountId());
        return dto;
    }
}
