package com.bharath.springcloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/showReg")
    public String showRegistrationForm() {
        return "registerUser";
    }
}
