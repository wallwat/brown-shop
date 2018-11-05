package com.example.brownshop.repository.app;

import com.example.brownshop.entity.app.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Long countByMemberId(Long id);
    List<Cart> findAllByMemberId(Long id);
}
