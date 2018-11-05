package com.example.brownshop.service;

import com.example.brownshop.entity.app.Cart;
import com.example.brownshop.entity.app.CartRequest;
import com.example.brownshop.entity.app.Product;
import com.example.brownshop.repository.app.CartRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jmx.export.annotation.ManagedOperation;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    private CartService cartService;
    public Cart cart;
    public Product product;
    public CartRequest cartRequest;

    @Mock
    CartRepo cartRepo;
    @Mock
    ProductService productService;
    @Mock
    CouponService couponService;

    @Before
    public void setup () {
        cartService = new CartService(productService, couponService, cartRepo);
        cart = new Cart(); // order
        product = new Product();
        cartRequest = new CartRequest();
        CartRequest.CartItemRequest cartItemRequest1 = new CartRequest.CartItemRequest().setProductId(1L).setAmount(1.0F).setPrice(50.0f);
        List<CartRequest.CartItemRequest> cartItemRequestList = Arrays.asList(cartItemRequest1);
        cartRequest.setCartItemRequestList(cartItemRequestList);
    }

    @Test
    public void createCartSuccessfully() {
        when(cartRepo.save(any())).thenReturn(cart);
        when(productService.getProductById(any())).thenReturn(product);
        when(cartRepo.countByMemberId(any())).thenReturn(1L);
        assertEquals(cart, cartService.createCart(cartRequest));
    }

    @Test
    public void createCartAddDiscount() {
        when(cartRepo.countByMemberId(any())).thenReturn(4L);
        when(cartRepo.save(any())).thenAnswer(i -> {
            return i.getArgument(0);
        });
        assertThat(cartService.createCart(cartRequest).getDiscount()).isEqualTo(10F);
    }

    @Test
    public void getOrderByMemberId() {
        Cart cart = new Cart();
        List<Cart> cartList = Arrays.asList(cart);
        when(cartRepo.findAllByMemberId(1L)).thenReturn(cartList);
        assertEquals(cartList, cartService.getOrderByMemberId(1L));
    }
}
