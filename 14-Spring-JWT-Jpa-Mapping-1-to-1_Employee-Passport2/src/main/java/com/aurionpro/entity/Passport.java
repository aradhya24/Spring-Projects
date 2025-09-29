package com.aurionpro.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "passport")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Passport {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String passportNumber;
	
	private LocalDate  issueDate;
	
	private LocalDate  expiryDate;

}
