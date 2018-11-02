package com.example.brownshop.Service;

import com.example.brownshop.Entity.Product;
import com.example.brownshop.Repository.ProductRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    private ProductService productService;
    public Product product;

    @Mock
    ProductRepo productRepo;

    @Before
    public void setup() {
        productService = new ProductService(productRepo);
        product = new Product().setName("car").setId(1L);
    }

    @Test
    public void createProductSuccessFully() {
        when(productRepo.save(any())).thenReturn(product);
        assertEquals(product, productService.createProduct(product));
    }

    @Test
    public void getProductById() {
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(product));
        assertEquals(product, productService.getProductById(1L));
    }
}