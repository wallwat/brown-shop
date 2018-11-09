package com.example.brownshop.entity.coupon;

import com.example.brownshop.entity.app.CartItem;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String type;
    private Float thresholdPrice;
    private Float thresholdQuantity;
    private String discountType;
    private Float discount;
    private Integer remaining;
}