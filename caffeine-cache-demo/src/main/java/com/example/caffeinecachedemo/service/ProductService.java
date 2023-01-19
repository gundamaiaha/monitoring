package com.example.caffeinecachedemo.service;

import com.example.caffeinecachedemo.exception.ProductNotFoundException;
import com.example.caffeinecachedemo.model.Product;
import com.example.caffeinecachedemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    @CacheEvict(value ="products" ,allEntries = true)
    @Override
    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @CacheEvict(value ="products" ,allEntries = true)
   // @CachePut(value = "products")
    @Override
    public Product updateProduct(final Long productId, final Product product) {
        final Product existingProduct = findProduct(productId);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return existingProduct;

    }

    @Cacheable(value = "products")
    @Override
    public Product findProduct(final Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException(productId));
    }

    @Cacheable(value = "products")
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();

    }

   // @CacheEvict(value = "products")
    @Override
    public void deleteProduct(final Long productId) {
        Product product = findProduct(productId);
        productRepository.delete(product);
    }

   // @CacheEvict(value = "products")
    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }


}
