package com.bharath.springcloud.controllers;

import com.bharath.springcloud.config.SecurityConfig;
import com.bharath.springcloud.model.Product;
import com.bharath.springcloud.repos.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
@Import(SecurityConfig.class)
class ProductRestControllerTest {

    private Product product1;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepo productRepoMock;

    // Not in use at the moment, just to allow the DI to inject it in the controller
    // TODO check how to Mock it
    @MockBean
    RestTemplateBuilder restTemplateBuilderMock;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setPrice(BigDecimal.TEN);
        product1.setName("USB-Storage");
        product1.setDescription("Storage Device");
        product1.setCouponCode("SUPERSALE");
    }

    @Test
    @WithMockUser() // role = {"USER"} per default
    void getProductByIDWithUserRole() throws Exception {
        // given
        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setCouponCode(product1.getCouponCode());
        savedProduct.setName(product1.getName());
        savedProduct.setPrice(product1.getPrice());
        savedProduct.setDescription(product1.getDescription());
        given(productRepoMock.findById(anyLong())).willReturn(Optional.of(savedProduct));

        // when then
        mockMvc.perform(get("/productapi/products/{productID}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(equalTo(1)))
                .andExpect(jsonPath("$.name").value(equalTo("USB-Storage")))
                .andDo(print());
    }

    @Test
    void getProductByIDUnauthenticated() throws Exception {
        // when then
        mockMvc.perform(get("/productapi/products/{productID}",1L))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}