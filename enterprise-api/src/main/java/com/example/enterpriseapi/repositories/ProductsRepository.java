package com.example.enterpriseapi.repositories;

import com.example.enterpriseapi.domain.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
