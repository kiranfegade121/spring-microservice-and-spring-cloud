package com.example.authdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
		       .withUser("anna").password(passwordEncoder.encode("anna@123")).roles("ADMIN")
		       .and()
		       .withUser("alex").password(passwordEncoder.encode("alex@123")).roles("USER")
		       .and()
		       .passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().logout();
		
		http.authorizeRequests()
		      .antMatchers("/admin/**")		      
		      .hasRole("ADMIN")
		      .antMatchers("/user/**")
		      .hasAnyRole("ADMIN", "USER")
		      .and()
		      .formLogin();
	}

}















