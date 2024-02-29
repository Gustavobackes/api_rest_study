package com.example.enterpriseapi.produtoInterface;

import com.example.enterpriseapi.domain.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsInterface extends JpaRepository<Products, Long> {
}
