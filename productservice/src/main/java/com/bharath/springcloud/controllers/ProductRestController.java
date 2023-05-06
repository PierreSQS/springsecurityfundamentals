package com.bharath.springcloud.controllers;

import com.bharath.springcloud.dto.Coupon;
import com.bharath.springcloud.model.Product;
import com.bharath.springcloud.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	private final ProductRepo productRepo;

	private final RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	public ProductRestController(ProductRepo productRepo, RestTemplate restTemplate) {
		this.productRepo = productRepo;
		this.restTemplate = restTemplate;
	}

	@PostMapping("/products")
	public Product create(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepo.save(product);

	}

	@GetMapping("/products/{productID}")
	public Product getProduct(@PathVariable Long productID) {
		return productRepo.findById(productID).orElse(null);
	}


}
