package com.bharath.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
        .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/couponapi/coupons/{code:^[A-Z]*$}")
                    .hasAnyRole("ADMIN","USER")
                    .requestMatchers(HttpMethod.POST,"/couponapi/coupons").hasRole("ADMIN")
        .and()
                .csrf().disable();

        return httpSecurity.build();
    }

}
