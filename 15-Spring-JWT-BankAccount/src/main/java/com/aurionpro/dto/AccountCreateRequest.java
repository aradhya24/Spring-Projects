package com.aurionpro.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class AccountCreateRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    // For creation, accountId can be generated, so optional
    private Long accountId;

    @NotBlank(message = "Account number is required")
    @Size(min = 6, max = 20, message = "Account number must be between 6 and 20 characters")
    private String accountNumber;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
    private BigDecimal balance;

    @NotNull(message = "Account active status is required")
    private Boolean isAccountActive;
}
