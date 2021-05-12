package com.training.jwtdemo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.training.jwtdemo.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");
		String jwtToken = null;
		
		System.out.println("Authorization Header" + authorizationHeader); 
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			jwtToken = authorizationHeader.substring(7);
			String username = jwtUtil.extractUsername(jwtToken);
			System.out.println("Username: " + username + " - " + SecurityContextHolder.getContext().getAuthentication() == null);
			
			
			if (username != null  && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails user = userDetailsService.loadUserByUsername(username);
				
				if (jwtUtil.validateToken(jwtToken, user)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
					System.out.println("Token validated");
				}
			}
		}
		filterChain.doFilter(request, response);
		
		
	}
}






