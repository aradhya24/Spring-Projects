package com.aurionpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.Customer;
import com.aurionpro.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

} 
