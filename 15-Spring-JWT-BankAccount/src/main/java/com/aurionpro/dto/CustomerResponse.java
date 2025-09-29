package com.aurionpro.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailid;
    private String contactno;
    private LocalDate dob;
    private AddressResponse address;
    private UserResponse user; // Added user info
    private List<AccountSummary> accounts;
    private List<TransactionSummary> transactions;
    private Boolean isCustomerActive;
}
