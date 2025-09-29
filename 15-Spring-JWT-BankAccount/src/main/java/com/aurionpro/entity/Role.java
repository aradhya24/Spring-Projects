package com.aurionpro.entity;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="roles")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Role {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	@Column
	private String rolename;
	
	@OneToMany(mappedBy = "role")
	@ToString.Exclude   // 🚀 prevents recursion
	private List<User> users;
	
	

}
