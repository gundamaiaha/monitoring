package com.example.caffeinecachedemo.service;

import com.example.caffeinecachedemo.model.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);

    @Transactional
    Product updateProduct(Long productId, Product product);

   Product findProduct(Long productId);

    List<Product> findAllProducts();

    void deleteProduct(Long productId);

    void deleteAllProducts();
}
