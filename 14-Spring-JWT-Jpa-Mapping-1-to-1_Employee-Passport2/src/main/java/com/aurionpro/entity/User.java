package com.aurionpro.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {
	@Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column
	private String username;
	@Column
	private String password;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(joinColumns = @JoinColumn(name="userId"), inverseJoinColumns = @JoinColumn(name="roleId"))	
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE,  CascadeType.DETACH})
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	

}