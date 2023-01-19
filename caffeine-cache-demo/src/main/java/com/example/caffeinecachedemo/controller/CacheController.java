package com.example.caffeinecachedemo.controller;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cache")
public class CacheController {


    private final CacheManager cacheManager;

    @GetMapping("/inspectCaches")
    public Map<Object, Object> inspectCache() {
        System.out.println("CacheNames : " + cacheManager.getCacheNames());

        Map<Object, Object> cacheMap = new HashMap<>();


        cacheManager.getCacheNames().forEach(cacheName -> {
            CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
            Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
            for (Map.Entry<Object, Object> entry : nativeCache.asMap().entrySet()) {
                System.out.println("Key = " + entry.getKey());
                System.out.println("Value = " + entry.getValue());
                cacheMap.put(entry.getKey(), entry.getValue());
            }

        });
        return cacheMap;

    }


}
