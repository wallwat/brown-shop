package com.example.brownshop.service;

import com.example.brownshop.entity.app.Cart;
import com.example.brownshop.entity.app.CartItem;
import com.example.brownshop.entity.app.CartRequest;
import com.example.brownshop.entity.app.Product;
import com.example.brownshop.repository.app.CartRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final ProductService productService;
    private final CouponService couponService;

    private final CartRepo cartRepo;
    public CartService(ProductService productService, CouponService couponService, CartRepo cartRepo) {
        this.productService = productService;
        this.couponService = couponService;
        this.cartRepo = cartRepo;
    }

    @Transactional
    public Cart createCart(CartRequest cart) {
        Cart order = new Cart()
                .setDiscount(0F)
                .setNet(cart.getNet())
                .setMemberId(cart.getMemberId())
                .setCartItem(this.initCartItem(cart.getCartItemRequestList()));

//        String coupon = cart.getCouponCode();

//        if (coupon != null && coupon.length() > 0) {
//            Float discountCoupon = couponService.calculateDiscountByCoupon(order, coupon);
//            System.out.println(discountCoupon);
//
//            if (discountCoupon > 0F) {
//                order.setDiscount(discountCoupon);
//                order.setCouponCode(coupon);
//            }
//        }

        System.out.println(cart.getCouponCode());

        Float discountCoupon = couponService.calculateDiscountCoupon(order, cart.getCouponCode());

        if (this.getCountByMemberId(cart.getMemberId()) >= 3) {
            // top discount 10%
            Float topDiscount = cart.getNet() * 0.1F;
            order.setDiscount(order.getDiscount() + topDiscount);
        }

//        if (true) {
//            throw new RuntimeException("sadas");
//        }

        return cartRepo.save(order);
    }

    public Long getCountByMemberId(Long memberId) {
        return cartRepo.countByMemberId(memberId);
    }

    public List<Cart> getOrderByMemberId(Long memberId) { return cartRepo.findAllByMemberId(memberId); }

    public List<CartItem> initCartItem(List<CartRequest.CartItemRequest> cartItems) {
        List<CartItem> items = new ArrayList<>();
        for (CartRequest.CartItemRequest data : cartItems) {
            Product product = productService.getProductById(data.getProductId());

            CartItem cartItem = new CartItem()
                    .setAmount(data.getAmount())
                    .setPrice(data.getPrice())
                    .setProduct(product);
            items.add(cartItem);
        }
        return items;
    }

}
