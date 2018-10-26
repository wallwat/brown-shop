package com.example.brownshop.Controller;

import com.example.brownshop.Entity.Cart;
import com.example.brownshop.Entity.CartItem;
import com.example.brownshop.Repository.CartItemRepo;
import com.example.brownshop.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getOrders() {
        return cartService.getOrders();
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(cart));
    }

//    @GetMapping("/{cartId}/item")
//    public List<CartItem> getCartItemByCartId(@PathVariable (value = "cartId") Long cartId) {
//        System.out.println(cartId);
//        return CartItemRepo.findByCartId(cartId);
//    }
}
