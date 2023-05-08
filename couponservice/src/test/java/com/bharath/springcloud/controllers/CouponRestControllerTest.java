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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CouponRestController.class})
@Import({SecurityConfig.class})
class CouponRestControllerTest {

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
    void createCouponAdmin() throws Exception {
        // given
        Coupon savedCoupon = new Coupon();
        savedCoupon.setCode(coupon.getCode());
        savedCoupon.setDiscount(coupon.getDiscount());
        savedCoupon.setExpDate(coupon.getExpDate());
        savedCoupon.setId(1L);

        given(couponRepoMock.save(any())).willReturn(savedCoupon);

        mockMvc.perform(post("/couponapi/coupons")
                        .with(user("mockadmin@bailey.com").password("pwd").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coupon)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.code").value("SUPERSALE"))
                .andDo(print());
    }

    @Test
    void createCouponNotAdmin() throws Exception {
        mockMvc.perform(post("/couponapi/coupons")
                        .with(user("mockuser@bailey.com").password("pwd").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coupon)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void getCouponUserAuthenticated() throws Exception {
        // given
        Coupon savedCoupon = new Coupon();
        savedCoupon.setCode(coupon.getCode());
        savedCoupon.setDiscount(coupon.getDiscount());
        savedCoupon.setExpDate(coupon.getExpDate());
        savedCoupon.setId(1L);

        given(couponRepoMock.findByCode(any())).willReturn(savedCoupon);

        // when , then
        mockMvc.perform(get("/couponapi/coupons/SUPERSALE")
                        .with(user("mockuser@bailey.com").password("pwd").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.code").value("SUPERSALE"))
                .andDo(print());
    }

    @Test
    void getCouponNotAuthenticated() throws Exception {
        mockMvc.perform(get("/couponapi/coupons/SUPERSALE"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
    @Test
    void getCouponAuthenticatedUserAskBadCodeFormat() throws Exception {
        mockMvc.perform(get("/couponapi/coupons/SUPERSALE123")
                .with(user("mockuser@bailey.com").password("pwd").roles("USER")))
                .andExpect(status().isForbidden())
                .andDo(print());
    }
}