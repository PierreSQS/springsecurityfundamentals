package com.bharath.springcloud.controllers;

import com.bharath.springcloud.config.SecurityConfig;
import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(CouponController.class)
@Import(SecurityConfig.class)
class CouponControllerTest {

    Coupon coupon;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CouponRepo couponRepoMock;

    @BeforeEach
    void setUp() {
        coupon = new Coupon();
        coupon.setCode("SUPERSALE");
        coupon.setDiscount(BigDecimal.TEN);
        coupon.setExpDate("12/12/2023");
    }

    @Test
    @WithMockUser(username = "MockUser", roles = {"USER"})
    void showCreateCouponAuthenticated() throws Exception {
        mockMvc.perform(get("/showCreateCoupon"))
                .andExpect(status().isOk())
                .andExpect(view().name("createCoupon"))
                .andDo(print());
    }

    @Test
    void saveAdminAuthenticated() throws Exception {
        // given
        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("code",coupon.getCode());
        multiValueMap.add("discount",coupon.getDiscount().toString());
        multiValueMap.add("expDate",coupon.getExpDate());

        mockMvc.perform(post("/saveCoupon")
                        .with(user("mockadmin@bailey.com").password("pwd").roles("ADMIN"))
                        .params(multiValueMap))
                .andExpect(status().isOk())
                .andExpect(view().name("createResponse"))
                .andDo(print());
    }

    @Test
    void showGetCoupon() {
    }

    @Test
    void getCoupon() {
    }
}