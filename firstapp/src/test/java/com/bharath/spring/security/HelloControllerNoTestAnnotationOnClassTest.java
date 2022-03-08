package com.bharath.spring.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// Doesn't work with these annotations, they are incomplete
// unlike Junit4,use @WebMvcTest instead for Junit5!!
// see the @WebMvcTest-Documentation
@Disabled("Disabled: see the comment on top of the test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MySecurityConfig.class})
@WebAppConfiguration
class HelloControllerNoTestAnnotationOnClassTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
    }

    @Test
    void helloUnauthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @WithMockUser
    @Test
    void helloAuthenticationWithMockuserOk() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Spring Security Rocks!!"))
                .andDo(print());
    }

    @Test
    void helloAuthenticationHttpBasicOk() throws Exception {
        mockMvc.perform(get("/hello").with(httpBasic("tom","cruise")))
                .andExpect(status().isOk())
                .andExpect(content().string("Spring Security Rocks!!"))
                .andDo(print());
    }
    @Test
    void helloAuthenticationWithUserOk() throws Exception {
        mockMvc.perform(get("/hello").with(user("tim"))) // user must not exist s. spring security documentation
                .andExpect(status().isOk())
                .andExpect(content().string("Spring Security Rocks!!"))
                .andDo(print());
    }



}