package com.aurionpro.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PassportResponseDto {
	
	private String passportNumber;
	private LocalDate issueDate;
	private LocalDate expiryDate;
	
}
