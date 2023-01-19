package com.example.caffeinecachedemo.repository;

import com.example.caffeinecachedemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
