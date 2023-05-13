package com.bharath.springcloud.controllers;

import com.bharath.springcloud.config.SecurityConfig;
import com.bharath.springcloud.model.Role;
import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repos.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Set;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {UserController.class})
@Import(SecurityConfig.class)
class UserControllerTest {

    // Begin - Test Data
    User user;
    MultiValueMap<String,String> multiValueMap;
    // End - Test Data


    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepo userRepoMock;

    @BeforeEach
    void setUp() {
        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");

        user = new User();
        user.setFirstName("MockUser");
        user.setLastName("MockTest");
        user.setEmail("mockuser@mocktest.com");
        user.setPassword("pwd");
        user.setRoles(Set.of(userRole));

        multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("firstName",user.getFirstName());
        multiValueMap.add("lastName",user.getLastName());
        multiValueMap.add("email",user.getEmail());
        multiValueMap.add("password", user.getPassword());
        multiValueMap.add("confirmPassword", user.getPassword());
    }

    @Test
    void showRegistrationForm() throws Exception {
        mockMvc.perform(get("/showReg"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerUser"))
                .andExpect(content().string(containsString("<title>Register User</title>")))
                .andDo(print());
    }

    @Test
    void registerUser() throws Exception {
        // given
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setPassword(user.getPassword());
        savedUser.setEmail(user.getEmail());
        savedUser.setRoles(user.getRoles());

        given(userRepoMock.save(any())).willReturn(savedUser);

        mockMvc.perform(post("/registerUser").params(multiValueMap))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
//                .andExpect(content().string(containsString("<title>Login User</title>")))
                .andDo(print());
    }
}