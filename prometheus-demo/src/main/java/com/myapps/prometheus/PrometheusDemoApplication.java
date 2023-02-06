package com.myapps.prometheus;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PrometheusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrometheusDemoApplication.class, args);
	}


	@Bean
	public TimedAspect timedAspect(MeterRegistry meterRegistry){
		return new TimedAspect(meterRegistry);
	}

	@GetMapping("/greet/{name}")
	@Timed(value = "greeting.time", description = "Time take to return greeting")
	public Greeting getGreeting(@PathVariable String name){
		return new Greeting("Hello "+name+", how are you doing!");
	}


}
