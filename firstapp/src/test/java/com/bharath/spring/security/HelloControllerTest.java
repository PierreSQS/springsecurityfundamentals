package com.bharath.spring.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void helloUnauthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(unauthenticated())
                .andDo(print());
    }

    @Test
    @WithMockUser
    void helloWithMockUser() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Spring Security Rocks!!")))
                .andDo(print());
    }

    @Test
    void helloWithAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/hello").with(httpBasic("tom","cruise")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Spring Security Rocks!!")))
                .andDo(print());
    }
}