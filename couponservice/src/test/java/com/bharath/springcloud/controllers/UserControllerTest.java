package com.bharath.springcloud.controllers;

import com.bharath.springcloud.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {UserController.class})
@Import(SecurityConfig.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

    }

    @Test
    void showRegistrationForm() throws Exception {
        mockMvc.perform(get("/showReg"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(content().string(containsString("<title>Register User</title>")))
                .andDo(print());
    }
}