package com.example.caffeinecachedemo.controller;

import com.example.caffeinecachedemo.model.Product;
import com.example.caffeinecachedemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody Product product) {
        return ResponseEntity
                .ok(productService.updateProduct(productId, product));
    }


//    @GetMapping("/{productId}")
//    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
//        return ResponseEntity.ok(productService.findProduct(productId));
//    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProducts() {
        productService.deleteAllProducts();
        return ResponseEntity.ok("All products deleted successfully!");
    }


}
