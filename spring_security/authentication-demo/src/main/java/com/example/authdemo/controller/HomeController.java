package com.example.authdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/greet")
	public String greet() {
		return "Good Morning!!!";
	}

	@GetMapping("/admin/page")
	public String adminPage() {
		return "Welcome, Admin!!!!";
	}
	
	@GetMapping("/user/page")
	public String userPage() {	
		return "Welcome, User!!!";
	}
}
