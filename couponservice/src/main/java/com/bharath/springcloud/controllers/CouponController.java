package com.bharath.springcloud.controllers;

import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CouponController {

    private final CouponRepo couponRepo;

    public CouponController(CouponRepo couponRepo) {
        this.couponRepo = couponRepo;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("showCreateCoupon")
    public String showCreateCoupon() {
        return "createCoupon";
    }

    @PostMapping(value = "saveCoupon")
    public String saveCoupon(@RequestBody Coupon coupon) {
        couponRepo.save(coupon);
        return "createCouponResponse";
    }
}
