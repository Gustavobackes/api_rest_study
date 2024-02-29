package com.example.enterpriseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EnterpriseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApiApplication.class, args);
    }

}
