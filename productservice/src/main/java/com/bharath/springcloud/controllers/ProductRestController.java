package com.bharath.springcloud.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.bharath.springcloud.dto.Coupon;
import com.bharath.springcloud.model.Product;
import com.bharath.springcloud.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	private final ProductRepo repo;

	private final RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	public ProductRestController(ProductRepo repo, RestTemplate restTemplate) {
		this.repo = repo;
		this.restTemplate = restTemplate;
	}

	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);

	}

	@GetMapping("/products/{id}")
	public Product findProductByID(@PathVariable Long id) {
		return repo.findById(id).orElse(null);
	}

}
