package com.example.brownshop;

import com.example.brownshop.Entity.Cart;
import com.example.brownshop.Entity.CartItem;
import com.example.brownshop.Entity.Member;
import com.example.brownshop.Entity.Product;
import com.example.brownshop.Repository.CartRepo;
import com.example.brownshop.Repository.MemberRepo;
import com.example.brownshop.Repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BrownShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrownShopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(MemberRepo memberRepo, ProductRepo productRepo, CartRepo cartRepo) {
		return args -> {
			Member member = new Member();
			member.setFirstName("Tony");
			member.setLastName("Ja");
			member.setMobilePhone("081-222-2222");
			member.setUsername("test");
			member.setPassword("1234");
			memberRepo.save(member);

			Product product1 = new Product();
			product1.setName("Gold fish");
			product1.setPrice(100);
			productRepo.save(product1);

			Product product2 = new Product();
			product2.setName("Gold duck");
			product2.setPrice(200);
			productRepo.save(product2);

			Cart cart = new Cart();
			cart.setNet(100);
			cart.setDiscount(0);
			cart.setMemberId("1");

			List<CartItem> item = Arrays.asList(
					new CartItem("1", "1", "100"),
					new CartItem("2", "1", "200")
			);
			cart.setCartItem(item);
			cartRepo.save(cart);
		};
	}
}
