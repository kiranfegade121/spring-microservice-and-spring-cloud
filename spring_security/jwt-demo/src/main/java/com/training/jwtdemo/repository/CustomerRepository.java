package com.training.jwtdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.jwtdemo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByEmail(String email);
}