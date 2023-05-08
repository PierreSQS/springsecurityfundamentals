package com.bharath.springcloud.controllers;

import com.bharath.springcloud.config.SecurityConfig;
import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@WebMvcTest(CouponController.class)
@Import(SecurityConfig.class)
class CouponControllerTest {

    Coupon coupon;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CouponRepo couponRepoMock;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        coupon = new Coupon();
        coupon.setCode("SUPERSALE");
        coupon.setDiscount(BigDecimal.TEN);
        coupon.setExpDate("12/12/2023");
    }

    @Test
    void showCreateCoupon() {
    }

    @Test
    void save() {
    }

    @Test
    void showGetCoupon() {
    }

    @Test
    void getCoupon() {
    }
}