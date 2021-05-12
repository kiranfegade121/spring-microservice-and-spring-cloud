package com.training.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.myapp.model.Customer;
import com.training.myapp.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		Customer savedCustomer = customerRepository.save(customer);
		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.OK);
	}
	
	@GetMapping("/admin/dashboard")
	public String adminPage() {
		return "Welcome Admin";
	}
	
	@GetMapping("/user/dashboard")
	public String userPage() {
		return "Welcome User";
	}
}













