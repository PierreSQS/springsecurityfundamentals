package com.bharath.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig  {

	@Bean
	public UserDetailsService userDetailService() {
		UserDetails user = User.withUsername("tom").password("{noop}cruise").roles("read").build();

		return new InMemoryUserDetailsManager(user);

	}

	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin();

		return http.build();
	}
}
