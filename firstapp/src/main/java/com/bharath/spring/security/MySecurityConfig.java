package com.bharath.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * New Configuration Style since 3.1.0 !!!!
 */
@Configuration
public class MySecurityConfig  {

	@Bean
	public UserDetailsService userDetailService() {
		UserDetails user = User.withUsername("tom")
				.password("{bcrypt}$2a$10$a6ymiGaZevkW.DYyZszpheB2sRxaQ/GhWb4YFJaLIPrBR/Tr2ce.y") // cruise
				.roles("read").build();

		return new InMemoryUserDetailsManager(user);

	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults()); // to make curl and postman tests possible

		return http.build();
	}
}
