package com.ppdd;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthServerApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(AuthServerApplication.class, args);
    }
}
