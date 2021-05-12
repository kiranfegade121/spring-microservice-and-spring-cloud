package com.training.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.training.myapp.service.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		       .passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.	      
		       authorizeRequests()		      
		      .antMatchers("/api/v1/admin/**")
		      .hasRole("ADMIN")
		      .antMatchers("/api/v1/user/**")
		      .hasAnyRole("ADMIN", "USER")
		      .antMatchers("/api/v1/customers")
		      .permitAll()
		      .and()
		      .formLogin()
		      .and().csrf().disable();		      
	}
}











