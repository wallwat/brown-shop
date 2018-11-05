package com.example.brownshop;

import com.example.brownshop.entity.app.Product;
import com.example.brownshop.entity.coupon.Coupon;
import com.example.brownshop.repository.app.ProductRepo;
import com.example.brownshop.repository.coupon.CouponRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BrownShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(BrownShopApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() { return  new BCryptPasswordEncoder();}

	@Bean
	CommandLineRunner runner(ProductRepo productRepo, CouponRepo couponRepo) {
		return args -> {
			Product product1 = new Product();
			product1.setName("Gold fish");
			product1.setPrice(100);
			productRepo.save(product1);

			Product product2 = new Product();
			product2.setName("Gold duck");
			product2.setPrice(200);
			productRepo.save(product2);

			Coupon coupon1 = new Coupon();
			coupon1.setCode("HOLIDAY");
			coupon1.setType("superman");
			coupon1.setThreshold(500F);
			coupon1.setAmount(50F);
			couponRepo.save(coupon1);

			Coupon coupon2 = new Coupon();
			coupon2.setCode("MONDAY");
			coupon2.setType("batman");
			coupon2.setThreshold(5F);
			coupon2.setAmount(50F);
			couponRepo.save(coupon2);
		};
	}
}
