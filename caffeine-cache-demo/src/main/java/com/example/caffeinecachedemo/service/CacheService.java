package com.example.caffeinecachedemo.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheService {

    private final CacheMetricsRegistrar cacheMetricsRegistrar;
    private final CacheManager cacheManager;


     @PostConstruct
    public void register() {
        // you have just registered cache "products"
        Cache productCache = this.cacheManager.getCache("products");
        this.cacheMetricsRegistrar.bindCacheToRegistry(productCache);
    }
}
