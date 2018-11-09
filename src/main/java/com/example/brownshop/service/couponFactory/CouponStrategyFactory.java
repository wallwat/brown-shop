package com.example.brownshop.service.couponFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CouponStrategyFactory {
    public CouponStrategy createCouponStrategy(String couponType) {
        switch (couponType) {
            case "PRICE": return new CouponPriceStrategy();
            case "QUANTITY": return new CouponQuantityStrategy();
            case "PRICE_QUANTITY": return new CouponPriceAndQuantityStrategy();
            default: return null;
        }
    }
}
