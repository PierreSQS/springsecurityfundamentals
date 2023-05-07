package com.bharath.springcloud.controllers;

import com.bharath.springcloud.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@WebMvcTest(CouponRestControllerTest.class)
@Import(SecurityConfig.class)
class CouponRestControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
    }

    @Test
    void getCoupon() {
    }
}