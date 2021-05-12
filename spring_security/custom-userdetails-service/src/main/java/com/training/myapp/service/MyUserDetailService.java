package com.training.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.myapp.model.Customer;
import com.training.myapp.repository.CustomerRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer customer = customerRepository.findByEmail(email);
		
		if(customer != null) 
			return customer;
		throw new UsernameNotFoundException("Customer Not Found");		
	}

}
