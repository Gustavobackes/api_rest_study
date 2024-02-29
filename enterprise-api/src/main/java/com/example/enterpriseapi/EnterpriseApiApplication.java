package com.example.enterpriseapi;

import com.example.enterpriseapi.domain.dtos.CategoriesDto;
import com.example.enterpriseapi.domain.dtos.ProductsDto;
import com.example.enterpriseapi.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;


@SpringBootApplication
@EnableFeignClients
public class EnterpriseApiApplication {
    @Autowired
    private ProductsService productsService;

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApiApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() {
        ProductsDto productsDto = ProductsDto.builder()
                .categoriesDtos(List.of(new CategoriesDto()))
                .id(1L)
                .nome("name test")
                .valor(23.5)
                .build();
        productsService.postNewProduct(productsDto);
    }
}
