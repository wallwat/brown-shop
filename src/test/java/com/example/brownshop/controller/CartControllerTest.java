package com.example.brownshop.controller;

import com.example.brownshop.service.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {
    private CartController cartController;

    @Mock
    CartService cartService;

    @Before
    public void setup() {
        cartController = new CartController(cartService);
    }

    @Test
    public void getOrderByMemberId() {
    }

    @Test
    public void createCart() {
    }
}