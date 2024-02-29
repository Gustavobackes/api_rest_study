package com.example.enterpriseapi.resources;

import com.example.enterpriseapi.domain.dtos.ProductsDto;
import com.example.enterpriseapi.services.ProductsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class ProductsResource {

    private final ProductsService service;

    public ProductsResource(ProductsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.getAllProducts(pageRequest);
    }

    @GetMapping("{productId}")
    public ResponseEntity<Object> getProductsById(@PathVariable("productId") Long productId){
        return service.getProductById(productId);
    }


    @PostMapping("/post")
    public ResponseEntity<Object> createProduct(@RequestBody ProductsDto productsDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.postNewProduct(productsDto));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Object> deleteProductById(@PathVariable("productId")Long productId){
        return service.deleteProduct(productId);
    }
}
