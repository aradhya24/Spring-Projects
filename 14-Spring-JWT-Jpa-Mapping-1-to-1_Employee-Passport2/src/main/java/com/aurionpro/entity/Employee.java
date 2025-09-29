package com.aurionpro.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "employee")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "passport_id", unique = true)
	private Passport passport;

	
	
	
	
}
