package com.aurionpro.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class DemoSecurityConfig {

	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails Aradhya = User.builder()
				.username("Aradhya")
				.password("{noop}test123")
				.roles("EMPLOYEE")
				.build();
		
		UserDetails omkar = User.builder()
				.username("omkar")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER")
				.build();
		
		UserDetails Srushti = User.builder()
				.username("Srushti")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(Aradhya,omkar,Srushti);
	}
	*/
	
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
	    JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

	    // ✅ Login can come from both `users` and `accounts`
	    userDetailsManager.setUsersByUsernameQuery(
	        "SELECT username, password, enabled FROM users WHERE username=? " +
	        "UNION " +
	        "SELECT account_name AS username, account_no AS password, enabled FROM accounts WHERE account_name=?"
	    );

	    // ✅ Roles from both sources
	    userDetailsManager.setAuthoritiesByUsernameQuery(
	        "SELECT username, authority FROM authorities WHERE username=? " +
	        "UNION " +
	        "SELECT account_name AS username, access_level AS authority FROM access WHERE account_name=?"
	    );

	    return userDetailsManager;
	}


	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(configurer -> configurer
				 .requestMatchers(HttpMethod.GET,"/api/employee").hasAnyRole("VIP","EMPLOYEE")
				 .requestMatchers(HttpMethod.GET,"/api/employee/**").hasAnyRole("EMPLOYEE","ADMIN","MANAGER")
				 .requestMatchers(HttpMethod.POST,"/api/employee").hasAnyRole("MANAGER","ADMIN")
				 .requestMatchers(HttpMethod.PATCH,"/api/employee/**").hasAnyRole("MANAGER","ADMIN")
				 .requestMatchers(HttpMethod.PUT,"/api/employee").hasAnyRole("MANAGER","ADMIN")
				 .requestMatchers(HttpMethod.DELETE,"/api/employee/**").hasRole("ADMIN"));
		
		//use HTTP Basic Authentication
		http.httpBasic(Customizer.withDefaults());
		
		//disable Cross Site Request Forgery(CSRF)
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	

}
