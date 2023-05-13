package com.bharath.springcloud.controllers;

import com.bharath.springcloud.model.Role;
import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repos.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/showReg")
    public String showRegistrationForm() {
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user) {
        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        user.setRoles(Set.of(userRole));
        user.setPassword(encodePWD(user.getPassword()));

        userRepo.save(user);
        return "redirect:/login";
    }

    private String encodePWD(String pwd) {
        return "{bcrypt}"+new BCryptPasswordEncoder().encode(pwd);
    }
}
