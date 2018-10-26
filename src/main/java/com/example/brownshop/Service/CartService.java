package com.example.brownshop.Service;

import com.example.brownshop.Entity.Cart;
import com.example.brownshop.Repository.CartRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Cart createCart(Cart cart) {
        return cartRepo.save(cart);
    }

    public List<Cart> getOrders() { return cartRepo.findAll(); }

}
