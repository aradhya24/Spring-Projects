package com.aurionpro.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EmployeeRequestDto {
    
	@NotBlank
	@Length(min = 2)
	private String firstName;
	
	@NotBlank
	@Length(min = 2)
	private String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	@Valid
	private PassportRequestDto passport;
	
	
	

}
