package com.aurionpro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.Role;


public interface RoleRepo extends JpaRepository<Role, Integer>{

	Optional<Role> findByRolename(String role);

}
