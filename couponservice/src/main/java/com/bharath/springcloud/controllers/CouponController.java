package com.bharath.springcloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CouponController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("showCreateCoupon")
    public String showCreateCoupon() {
        return "createCoupon";
    }

}
