package com.example.brownshop.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
//@Table(name = "order")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="cart_id")
    private List<CartItem> cartItem;

    private float net;
    private float discount;
    private String memberId;
}
