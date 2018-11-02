package com.example.brownshop;

import com.example.brownshop.Entity.Product;
import com.example.brownshop.Repository.CartRepo;
import com.example.brownshop.Repository.MemberRepo;
import com.example.brownshop.Repository.ProductRepo;
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
	CommandLineRunner runner(MemberRepo memberRepo, ProductRepo productRepo, CartRepo cartRepo) {
		return args -> {
			Product product1 = new Product();
			product1.setName("Gold fish");
			product1.setPrice(100);
			productRepo.save(product1);

			Product product2 = new Product();
			product2.setName("Gold duck");
			product2.setPrice(200);
			productRepo.save(product2);
		};
	}
}
