package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dto.TransactionResponse;

public interface EmailService {

    // Generic
    void sendEmail(String to, String subject, String body);

    // Customer Registration
    void sendCustomerRegistrationEmail(String email, Long customerId);

    // Account Status
    void sendAccountStatusEmail(String email, Long accountId, boolean isActivated);

    // Transaction Alerts
    void sendTransactionAlertEmail(String email, Long accountId, String type, String remarks, String amount);

    // Passbook
    void sendPassbookEmail(String email, String passbookDetails, Long accountId);

    // Passbook PDF
    void sendPassbookPdfEmail(String email, Long accountId, List<TransactionResponse> passbook);
}
