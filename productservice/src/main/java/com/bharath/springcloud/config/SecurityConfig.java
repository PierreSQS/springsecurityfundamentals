package com.bharath.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * New Config since SB3.1.0 !!!!
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
             .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,"/productapi/products/{productID:^[1-9]*$}")
                            .hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.POST,"/productapi/products").hasRole("ADMIN"))

            .csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}
