package com.bharath.springcloud.controllers;

import com.bharath.springcloud.model.Product;
import com.bharath.springcloud.repos.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ProductRestController.class})
class ProductRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepo productRepoMock;

    Product productMock;

    @MockBean
    RestTemplate restTemplateMock;

    @BeforeEach
    void setUp() {
        productMock = new Product();
        productMock.setId(1L);
        productMock.setName("Iphone");
        productMock.setDescription("Mobile Device");
        productMock.setCouponCode("SUPERSALE");
    }

    @Disabled("Not implemented yet!")
    @Test
    void createProduct() {
    }

    @Test
    void findProductByID() throws Exception {
        // Given
        given(productRepoMock.findById(anyLong())).willReturn(Optional.of(productMock));

        // When, Then
        mockMvc.perform(get("/productapi/products/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(equalTo("Mobile Device")))
                .andDo(print());
    }
}