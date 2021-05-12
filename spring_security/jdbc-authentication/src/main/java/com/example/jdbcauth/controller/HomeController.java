package com.example.jdbcauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
		
	@GetMapping("/")
	public String greet() {
		return "Welcome";			
	}
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard() {
		return "Welcome Admin";
	}
	
	@GetMapping("/user/dashboard")
	public String userDashboard() {
		return "Welcome User";
	}
	
	@GetMapping("/aboutus")
	public String aboutUs() {
		return "AboutUs Page";
	}
}
