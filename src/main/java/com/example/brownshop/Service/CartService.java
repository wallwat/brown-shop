package com.example.brownshop.Service;

import com.example.brownshop.Entity.Cart;
import com.example.brownshop.Entity.CartItem;
import com.example.brownshop.Entity.CartRequest;
import com.example.brownshop.Entity.Product;
import com.example.brownshop.Repository.CartRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartService {

    private final ProductService productService;

    private final CartRepo cartRepo;
    public CartService(ProductService productService, CartRepo cartRepo) {
        this.productService = productService;
        this.cartRepo = cartRepo;
    }

    public Cart createCart(CartRequest cart) {
        List<CartItem> items = new ArrayList<>();

        for (CartRequest.CartItemRequest data : cart.getCartItemRequestList()) {
            Product product = productService.getProductById(data.getProductId());

            CartItem cartItem = new CartItem()
                    .setAmount(data.getAmount())
                    .setPrice(data.getPrice())
                    .setProduct(product);
            items.add(cartItem);
        }
        Cart order = new Cart()
                .setDiscount(0F)
                .setNet(cart.getNet())
                .setMemberId(cart.getMemberId())
                .setCartItem(items);

        Long count = cartRepo.countByMemberId(cart.getMemberId());
        if (count >= 3) {
            order.setDiscount(10);
        }

        return cartRepo.save(order);
    }

    public List<Cart> getOrderByMemberId(Long memberId) { return cartRepo.findAllByMemberId(memberId); }

}
