package com.bharath.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * PWD-Encoder is even not needed, since passwords are already encoded in the DB,
 * thus per default the following PWD is set: PasswordEncoderFactories.createDelegatingPasswordEncoder()
 */
@Configuration
public class SecurityConfig {

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
        .and()
                .formLogin().loginPage("/login").permitAll()
        .and()
                .authorizeHttpRequests()
                .requestMatchers("/","/showReg","registerUser","/resources/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/couponapi/coupons/{code:^[A-Z]*$}","/showGetCoupon")
                    .hasAnyRole(ADMIN_ROLE, USER_ROLE)
                .requestMatchers(HttpMethod.POST,"/couponapi/coupons","/saveCoupon").hasRole(ADMIN_ROLE)
                .requestMatchers(HttpMethod.POST,"/getCoupon").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                .requestMatchers(HttpMethod.GET,"/showCreateCoupon").hasRole(ADMIN_ROLE)
        .and()
                .csrf().disable();

        return httpSecurity.build();
    }

}
