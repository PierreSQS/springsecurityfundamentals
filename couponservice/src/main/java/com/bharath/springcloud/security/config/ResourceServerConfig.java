package com.bharath.springcloud.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    public static final String RESOURCEID = "couponservice";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCEID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .mvcMatchers(HttpMethod.GET,"/couponapi/coupons/{code:^[A-Z]*$}").hasAnyRole("ADMIN, USER")
                    .mvcMatchers(HttpMethod.POST,"/couponapi/coupons").hasRole("ADMIN")
                    .anyRequest().denyAll()
                .and()
                    .csrf().disable();
    }

}
