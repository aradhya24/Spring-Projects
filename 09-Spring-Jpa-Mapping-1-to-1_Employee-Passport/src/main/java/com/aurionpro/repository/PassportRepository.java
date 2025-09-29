package com.aurionpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long>{

}
