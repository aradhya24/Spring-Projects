package com.aurionpro.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeResponseDto {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private PassportResponseDto passport;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public PassportResponseDto getPassport() {
		return passport;
	}
	public void setPassport(PassportResponseDto passport) {
		this.passport = passport;
	}
	
	
	
	
	
}
