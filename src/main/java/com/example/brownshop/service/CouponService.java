package com.example.brownshop.service;

import com.example.brownshop.entity.coupon.Coupon;
import com.example.brownshop.repository.coupon.CouponRepo;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
    private final CouponRepo couponRepo;

    public CouponService(CouponRepo couponRepo) {
        this.couponRepo = couponRepo;
    }

    public Coupon getCouponByCode(String code) {
        return couponRepo.findByCode(code);
    }
}
