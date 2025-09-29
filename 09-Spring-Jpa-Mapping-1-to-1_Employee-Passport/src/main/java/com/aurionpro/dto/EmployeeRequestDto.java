package com.aurionpro.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PassportRequestDto getPassport() {
		return passport;
	}
	public void setPassport(PassportRequestDto passport) {
		this.passport = passport;
	}
	
	

}
