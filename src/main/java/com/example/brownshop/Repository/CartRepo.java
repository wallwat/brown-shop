package com.example.brownshop.Repository;

import com.example.brownshop.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Long countByMemberId(Long id);
    List<Cart> findAllByMemberId(Long id);
}
