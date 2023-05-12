package com.bharath.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
        .and()
                .formLogin().loginPage("/login").permitAll()
        .and()
                .authorizeHttpRequests()
                .requestMatchers("/","/resources/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/couponapi/coupons/{code:^[A-Z]*$}","/showGetCoupon")
                    .hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST,"/couponapi/coupons","/saveCoupon").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/getCoupon").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/showCreateCoupon").hasRole("ADMIN")
        .and()
                .csrf().disable();

        return httpSecurity.build();
    }

}
