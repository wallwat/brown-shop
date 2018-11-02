package com.example.brownshop.Controller;

import com.example.brownshop.Entity.Cart;
import com.example.brownshop.Entity.CartRequest;
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

    @GetMapping("/{memberId}")
    public ResponseEntity<List<Cart>> getOrderByMemberId(@PathVariable (value = "memberId") Long memberId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getOrderByMemberId(memberId));
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody CartRequest cart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(cart));
        //return new ResponseEntity<Cart>(HttpStatus.OK);
    }

//    @GetMapping("/{cartId}/item")
//    public List<CartItem> getCartItemByCartId(@PathVariable (value = "cartId") Long cartId) {
//        System.out.println(cartId);
//        return CartItemRepo.findByCartId(cartId);
//    }
}
