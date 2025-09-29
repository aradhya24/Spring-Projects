package com.aurionpro.service;

import java.util.List;
import com.aurionpro.dto.AccountCreateRequest;
import com.aurionpro.dto.AccountSummary;

public interface AccountService {
	AccountSummary createAccount(AccountCreateRequest request);

	List<AccountSummary> getAccountsByCustomerId(Long customerId);

	AccountSummary getAccountById(Long accountId);
	
	List<AccountSummary> getAllAccounts();
	
	String activateAccount(Long accountId);
	
	String inActivateAccount(Long accountId);
}
