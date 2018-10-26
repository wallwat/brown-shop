package com.example.brownshop.Repository;

import com.example.brownshop.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
