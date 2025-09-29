package com.aurionpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.Address;
import com.aurionpro.entity.Customer;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
