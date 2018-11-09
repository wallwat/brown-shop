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
			coupon1.setCode("MONDAY");
			coupon1.setType("PRICE");
			coupon1.setThresholdPrice(500F);
			coupon1.setDiscountType("BATH");
			coupon1.setDiscount(50F);
			coupon1.setRemaining(5);
			couponRepo.save(coupon1);

			Coupon coupon2 = new Coupon();
			coupon2.setCode("TUESDAY");
			coupon2.setType("QUANTITY");
			coupon2.setThresholdQuantity(10F);
			coupon2.setDiscountType("BATH");
			coupon2.setDiscount(50F);
			coupon2.setRemaining(5);
			couponRepo.save(coupon2);

			Coupon coupon3 = new Coupon();
			coupon3.setCode("WEDNESDAY");
			coupon3.setType("PRICE_QUANTITY");
			coupon3.setThresholdPrice(500F);
			coupon3.setThresholdQuantity(10F);
			coupon3.setDiscountType("BATH");
			coupon3.setDiscount(50F);
			coupon3.setRemaining(5);
			couponRepo.save(coupon3);

			Coupon coupon4 = new Coupon();
			coupon4.setCode("THURSDAY");
			coupon4.setType("PRICE");
			coupon4.setThresholdPrice(5000F);
			coupon4.setDiscountType("PERCENT");
			coupon4.setDiscount(10F);
			coupon4.setRemaining(5);
			couponRepo.save(coupon4);
		};
	}
}
