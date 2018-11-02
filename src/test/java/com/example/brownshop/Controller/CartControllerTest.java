package com.example.brownshop.Controller;

import com.example.brownshop.Service.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

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