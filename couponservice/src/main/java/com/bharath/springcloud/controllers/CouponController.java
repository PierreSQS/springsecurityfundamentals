package com.bharath.springcloud.controllers;

import com.bharath.springcloud.model.Coupon;
import com.bharath.springcloud.repos.CouponRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("saveCoupon")
    public String saveCoupon(Coupon coupon) {
        couponRepo.save(coupon);
        return "createCouponResponse";
    }

    @GetMapping("showGetCoupon")
    public String showGetCoupon() {
        return "getCoupon";
    }

    @PostMapping("getCoupon")
    public String getCoupon(String code, Model model) {
        model.addAttribute("coupon",couponRepo.findByCode(code));
        return "couponDetails";
    }
}
