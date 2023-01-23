//package com.example.ehcache.config;
//
//
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.Gauge;
//import io.micrometer.core.instrument.MeterRegistry;
//import io.prometheus.client.CollectorRegistry;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
//
//@Service
//public class EhcacheMetricsCollector {
//
//
//
//    private final CacheManager cacheManager;
//    private final CollectorRegistry registry;
//
//    public EhcacheMetricsCollector(CacheManager cacheManager, CollectorRegistry registry) {
//        this.cacheManager = cacheManager;
//        this.registry = registry;
//    }
//
//    @PostConstruct
//    public void register() {
//        // Collect and expose the metrics for each cache in the cache manager
//        for (String cacheName :cacheManager.getCacheNames()) {
//            Cache cache = cacheManager.getCache(cacheName);
//
//
//
//            Gauge.build("ehcache_cache_size", cache, c -> c.getSize())
//                    .tags("cache", cacheName)
//                    .register(registry);
//            Counter.build("ehcache_cache_gets", cache, c -> c.getStatistics().cacheHitCount())
//                    .tags("cache", cacheName)
//                    .register(registry);
//            Counter.build("ehcache_cache_puts", cache, c -> c.getStatistics().cacheMissCount())
//                    .tags("cache", cacheName)
//                    .register(registry);
//        }
//    }
//}
//
//}
