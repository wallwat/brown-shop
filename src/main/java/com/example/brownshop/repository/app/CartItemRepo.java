package com.example.brownshop.repository.app;

import com.example.brownshop.entity.app.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
