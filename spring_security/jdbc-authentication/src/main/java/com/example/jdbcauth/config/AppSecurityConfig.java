package com.example.jdbcauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth.jdbcAuthentication()
                 .dataSource(dataSource)
                 .usersByUsernameQuery("select username, password, enabled from customers where username=?")
                 .authoritiesByUsernameQuery("select username, role from roles where username=?")
                 .passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		      .antMatchers("/api/v1/admin/**")
		      .hasRole("ADMIN")
		      .antMatchers("/api/v1/user/**")
		      .hasAnyRole("USER", "ADMIN")
		      .antMatchers("/api/v1/","/api/v1/aboutus")
		      .permitAll()
		      .and()
		      .formLogin();
		
	}
}















