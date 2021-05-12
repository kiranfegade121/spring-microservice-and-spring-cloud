package com.training.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.myapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByEmail(String email);
}
