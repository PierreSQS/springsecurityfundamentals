package com.bharath.springcloud.controllers;

import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {CouponController.class})
class CouponControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CouponRepo couponRepoMock;

    @Captor
    ArgumentCaptor<Coupon> couponArgumentCaptor;

    private Coupon coupon;

    @BeforeEach
    void setUp() {
        coupon = new Coupon();
        coupon.setCode("SUPERSALE");
        coupon.setDiscount(BigDecimal.valueOf(10));
        coupon.setExpDate("22-04-2021");
    }
    @WithMockUser
    @Test
    void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("Get Coupon")))
                .andDo(print());
    }

    @Test
    void showCreateCoupon() throws Exception {
        mockMvc.perform(get("/showCreateCoupon").with(user("pierrot mockuser")))
                .andExpect(status().isOk())
                .andExpect(view().name("createCoupon"))
                .andExpect(content().string(containsString("Create Coupon")))
                .andDo(print());
    }

    @Test
    void saveCoupon() throws Exception {
        // Given
        given(couponRepoMock.save(couponArgumentCaptor.capture())).willReturn(coupon);

        // When, Then
        mockMvc.perform(post("/saveCoupon")
                        .with(user("pierrot mockadmin")
                                .roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("code=SUPERSALE&discount=15&expDate=15-08-2022"))
                .andExpect(status().isOk())
                .andExpect(view().name("createCouponResponse"))
                .andExpect(content().string(containsString("Create Coupon Response")))
                .andDo(print());

        assertThat(couponArgumentCaptor.getValue().getCode()).isEqualToIgnoringCase("SUPERSALE");
    }

    @Test
    void getCoupon() throws Exception {
        // Given
        given(couponRepoMock.findByCode(anyString())).willReturn(coupon);

        // When, Then
        mockMvc.perform(post("/getCoupon")
                        .with(user("pierrot mockadmin").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("code=SUPERSALE"))
                .andExpect(status().isOk())
                .andExpect(view().name("couponDetails"))
                .andExpect(model().attributeExists( "coupon"))
                .andExpect(content().string(containsString("Code:<b>SUPERSALE</b>")))
                .andExpect(content().string(containsString("Discount:<b>10</b>")))
                .andExpect(content().string(containsString("Expiry Date:<b>22-04-2021</b>")))
                .andDo(print());

    }
}