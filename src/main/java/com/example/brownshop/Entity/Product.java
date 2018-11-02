package com.example.brownshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty("product_id")
    private Long id;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItem;

    private String name;
    private float price;

}
