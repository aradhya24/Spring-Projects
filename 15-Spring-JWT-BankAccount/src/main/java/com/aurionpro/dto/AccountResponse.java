package com.aurionpro.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {
    private Long customerId;
    private Long accountId;
    private String accountNumber;
    private String accountType; 
    private BigDecimal balance;
    private List<TransactionResponse> transactions;
    private Boolean isAccountActive;
}
