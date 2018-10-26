package com.example.brownshop.Service;

import com.example.brownshop.Entity.Product;
import com.example.brownshop.Repository.ProductRepo;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product creatProduct(Product product) { return productRepo.save(product); }
}
