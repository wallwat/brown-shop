package com.example.brownshop.service;

import com.example.brownshop.entity.app.Product;
import com.example.brownshop.repository.app.ProductRepo;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product createProduct(Product product) { return productRepo.save(product); }

    public Product getProductById(Long productId) { return productRepo.findById(productId).orElseThrow(() -> new RuntimeException("gggggg")); }
}
