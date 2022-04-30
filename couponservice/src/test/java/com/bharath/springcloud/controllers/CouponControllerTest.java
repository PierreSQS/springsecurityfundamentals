package com.bharath.springcloud.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {CouponController.class})
class CouponControllerTest {

    @Autowired
    MockMvc mockMvc;

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

}