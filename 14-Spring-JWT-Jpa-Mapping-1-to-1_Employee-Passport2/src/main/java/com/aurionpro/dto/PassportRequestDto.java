package com.aurionpro.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PassportRequestDto {
    
	@Length(min = 9, max = 9)
    @Pattern(
            regexp = "^[A-Z]{2}[0-9]{7}$",
            message = "Passport number must be 2 uppercase letters followed by 7 digits"
        )
	private String passportNumber;

	
	@PastOrPresent
	@NotNull
	private LocalDate issueDate;
	
	@Future
	@NotNull
	private LocalDate expiryDate;

	
}
