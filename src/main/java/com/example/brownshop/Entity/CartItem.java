package com.example.brownshop.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private String amount;
    private String price;

    public CartItem(String productId, String amount, String price) {
        this.productId = productId;
        this.amount = amount;
        this.price = price;
    }
}
