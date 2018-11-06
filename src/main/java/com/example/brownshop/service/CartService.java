package com.example.brownshop.service;

import com.example.brownshop.entity.app.Cart;
import com.example.brownshop.entity.app.CartItem;
import com.example.brownshop.entity.app.CartRequest;
import com.example.brownshop.entity.app.Product;
import com.example.brownshop.repository.app.CartRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartService {

    private final ProductService productService;
    private final CouponService couponService;

    private final CartRepo cartRepo;
    public CartService(ProductService productService, CouponService couponService, CartRepo cartRepo) {
        this.productService = productService;
        this.couponService = couponService;
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

        String coupon = cart.getCouponCode();
        Cart order = new Cart()
                .setDiscount(0F)
                .setNet(cart.getNet())
                .setMemberId(cart.getMemberId())
                .setCartItem(items);

        if (coupon != null && coupon.length() > 0) {
            Float discountCoupon = couponService.calculateDiscountByCoupon(order, coupon);
            System.out.println(discountCoupon);

            if (discountCoupon > 0F) {
                order.setDiscount(discountCoupon);
                order.setCouponCode(coupon);
            }
        }

        Long count = cartRepo.countByMemberId(cart.getMemberId());
        if (count >= 3) {
            // top discount 10%
            Float topDiscount = cart.getNet() * 0.1F;
            order.setDiscount(order.getDiscount() + topDiscount);
        }
        return cartRepo.save(order);
    }

    public List<Cart> getOrderByMemberId(Long memberId) { return cartRepo.findAllByMemberId(memberId); }

}
