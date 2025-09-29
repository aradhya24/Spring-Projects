package com.aurionpro.model;


import org.springframework.web.bind.annotation.InitBinder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Student {
	
	@NotNull(message = "is required")
	@Size(min=1,message = "is required")
	String firstName;
	
	@NotNull(message = "is required")
	@Size(min=1,message = "is required")
	String lastName;

	
	public Student(){
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

	
	
	
}
