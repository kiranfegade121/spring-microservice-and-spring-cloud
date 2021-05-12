package com.training.jwtdemo.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.jwtdemo.model.AuthorizeRequest;
import com.training.jwtdemo.model.AuthorizeResponse;
import com.training.jwtdemo.util.JwtUtil;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/hello")
	public String greet() {
		return "Good morning";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthorizeResponse>  authenticateUser(@RequestBody AuthorizeRequest authorizeRequest) throws AuthenticationException {
		 
		// First authenticate user and if it authenticated, generate JWT token.
		
		String username = authorizeRequest.getUsername();
		String password = authorizeRequest.getPassword();
		
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);			
			authenticationManager.authenticate(authentication);
		}
		catch(BadCredentialsException ex) {
			throw new AuthenticationException("Invalid username or password");
		}
		catch(DisabledException ex) {
			throw new AuthenticationException("Your account is disabled.");
		}
		catch(LockedException ex) {
			throw new AuthenticationException("Your account is locked.");
		}
		 
		UserDetails user = userDetailsService.loadUserByUsername(username);
		String jwtToken = jwtUtil.generateToken(user);
		System.out.println("JWT Token: " + jwtToken);
		
		AuthorizeResponse response = new AuthorizeResponse(jwtToken);
		
		return new ResponseEntity<AuthorizeResponse>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
}















