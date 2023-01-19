package com.example.caffeinecachedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CaffeineCacheDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaffeineCacheDemoApplication.class, args);
	}

}
