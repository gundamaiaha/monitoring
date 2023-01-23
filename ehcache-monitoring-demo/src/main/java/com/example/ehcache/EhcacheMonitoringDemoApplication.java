package com.example.ehcache;

import io.micrometer.core.instrument.MeterRegistry;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.core.Ehcache;
import org.ehcache.core.EhcacheManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class EhcacheMonitoringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EhcacheMonitoringDemoApplication.class, args);
	}

//	@Bean
//	public CacheManager cacheManager(MeterRegistry registry) {
//		return new Ehcache(
//				CacheManagerBuilder.newCacheManagerBuilder()
//						.using(registry)
//						.build());
//	}
}
