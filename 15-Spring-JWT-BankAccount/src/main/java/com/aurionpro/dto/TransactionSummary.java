package com.aurionpro.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSummary {
    private Long transId;
    private String transType;
    private BigDecimal amount;
}