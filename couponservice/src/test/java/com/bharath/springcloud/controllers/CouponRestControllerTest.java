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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CouponRestControllerTest.class})
@Import({SecurityConfig.class})
class CouponRestControllerTest {

    private Coupon coupon;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CouponRepo couponRepoMock;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private CouponRepo couponRepo;

    @BeforeEach
    void setUp() {
        coupon = new Coupon();
        coupon.setCode("SUPERSALE");
        coupon.setDiscount(BigDecimal.TEN);
        coupon.setExpDate("12/12/2023");
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/couponapi/coupons")
                        .with(user("mock@bailey.com").password("pwd").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coupon)))
                .andExpect(status().isOk());
    }

    @Test
    void getCoupon() {
    }
}