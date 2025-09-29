package com.aurionpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.Account;
import com.aurionpro.entity.Customer;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
