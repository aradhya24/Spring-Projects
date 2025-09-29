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
public class EmployeeResponseDto {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private PassportResponseDto passport;

	
	
	
	
}
