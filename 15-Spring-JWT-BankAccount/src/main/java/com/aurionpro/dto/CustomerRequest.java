package com.aurionpro.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
	@NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String emailid;

    @NotBlank(message = "Contact number is required")
    private String contactno;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    @Valid
    @NotNull(message = "Address is required")
    private AddressRequest address;

    @NotNull(message = "Customer active status is required")
    private Boolean isCustomerActive;

    // User details for login
    private UserRequest user;
}
