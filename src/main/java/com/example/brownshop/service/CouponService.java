package com.example.brownshop.service;

import com.example.brownshop.entity.app.Cart;
import com.example.brownshop.entity.app.CartItem;
import com.example.brownshop.entity.coupon.Coupon;
import com.example.brownshop.repository.coupon.CouponRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {
    private final CouponRepo couponRepo;

    public CouponService(CouponRepo couponRepo) {
        this.couponRepo = couponRepo;
    }

    public Float calculateDiscountByCoupon(Cart order, String coupon) {
        Float discount = 0F;
        Coupon res = couponRepo.findByCode(coupon);

        String type = res.getType();
        if (type.equals("A")) {
            discount = this.calculateDiscountWithNet(order.getNet(), res.getThreshold(), res.getAmount());
        } else if (type.equals("B")) {
            discount = this.calculateDiscountWithItem(order.getCartItem(), res.getThreshold(), res.getAmount());
        }
        return order.getDiscount() + discount;
    }

    public Float calculateDiscountWithNet(Float net, Float threshold, Float amount) {
        if (net >= threshold) {
            return amount;
        }
        return 0F;
    }

    public  Float calculateDiscountWithItem(List<CartItem> cartItems, Float threshold, Float amount) {
        int i;
        double sumItem = 0;
        for (i = 0; i < cartItems.size(); i++)
            sumItem += cartItems.get(i).getAmount();

        if (sumItem >= threshold) {
            return amount;
        }
        return 0F;
    }
}
