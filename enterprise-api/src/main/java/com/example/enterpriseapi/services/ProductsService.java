package com.example.enterpriseapi.services;

import com.example.enterpriseapi.domain.dtos.EmailDto;
import com.example.enterpriseapi.domain.dtos.ProductsDto;
import com.example.enterpriseapi.domain.entities.Products;
import com.example.enterpriseapi.feign.EmailFeign;
import com.example.enterpriseapi.repositories.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductsService {

    private final ProductsRepository repository;
    private final ModelMapper modelMapper;
    private final EmailFeign emailFeign;

    public ProductsService(ProductsRepository repository, ModelMapper modelMapper, EmailFeign emailFeign) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.emailFeign = emailFeign;
    }


    //conversation of Entity to a Dto
    private ProductsDto toProductDto(Products products) {
        return modelMapper.map(products, ProductsDto.class);
    }


    public ResponseEntity<Object> getAllProducts(PageRequest pageRequest) {
        Page<Object> page = repository.findAll(pageRequest).map(this::toProductDto);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<Object> getProductById(Long productId) {
        boolean exist = repository.existsById(productId);
        if (!exist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com id " + productId + " não existe!");
        }
        Optional<ProductsDto> productsDto = repository.findById(productId).map(this::toProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(productsDto);
    }

    @Transactional
    public Products postNewProduct(ProductsDto productsDto) {
        Products products = new Products(productsDto);
        EmailDto emailDto = EmailDto.builder()
                .emailFrom("kitzgustavo@gmail.com")
                .emailTo("gustareserv@gmail.com")
                .ownerRef("title test")
                .text("text test")
                .subject("subject test")
                .build();
        emailFeign.sendingEmail(emailDto);
        return repository.save(products);
    }

    public ResponseEntity<Object> deleteProduct(Long productId) {
        boolean exist = repository.existsById(productId);
        if (!exist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Produto com id %d não existe!", productId));
        }
        repository.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Produto com id %d foi excluido!", productId));
    }


}
