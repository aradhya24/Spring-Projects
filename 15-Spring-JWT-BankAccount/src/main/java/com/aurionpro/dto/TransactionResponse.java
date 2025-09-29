package com.aurionpro.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long transId;
    private Long customerId;
    private Long accountId;
    private Long toAccountId;
    private BigDecimal amount;
    private String transType;
    private String remarks;
    private LocalDateTime date;
    private BigDecimal balanceAfterTxn;
}
