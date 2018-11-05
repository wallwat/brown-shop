package com.example.brownshop.entity.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class CartRequest {
    @JsonProperty("cartItem")
    private List<CartItemRequest> cartItemRequestList;
    private float net;
    private Long memberId;
    private String couponCode;

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class CartItemRequest {
        @JsonProperty("product_id")
        private Long productId;
        private Float amount;
        private Float price;

        @Override
        public String toString() {
            return "CartItemRequest{" +
                    "productId=" + productId +
                    ", amount=" + amount +
                    ", price=" + price +
                    '}';
        }
    }
}
