package com.example.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.feign.feign",
        "com.example.feign.controller"})
@EnableCircuitBreaker
public class FeignApplication {

    public static void main(String[] args) {
//        SpringApplication.run(FeignApplication.class, args);
        run(args);
    }

    public static ConfigurableApplicationContext run(String[] args) {
        return SpringApplication.run(FeignApplication.class, args);
    }
}
