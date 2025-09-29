package com.aurionpro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

	Optional<Customer> findByUserUsername(String username);

}
