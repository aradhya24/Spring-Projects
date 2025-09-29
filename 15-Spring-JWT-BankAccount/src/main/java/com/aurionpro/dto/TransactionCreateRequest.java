package com.aurionpro.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "From Account ID is required")
    private Long fromAccountId;

    private Long toAccountId; // optional, only for transfer

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Transaction type is required")
    private String transType; // DEBIT, CREDIT, TRANSFER

    private String remarks;
}
