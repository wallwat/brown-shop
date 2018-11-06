package com.example.brownshop.entity.app;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="cart_id")
    private List<CartItem> cartItem;

    private Float net;
    private Float discount;

//    @ManyToOne
    private Long memberId;

    private String couponCode;
}
