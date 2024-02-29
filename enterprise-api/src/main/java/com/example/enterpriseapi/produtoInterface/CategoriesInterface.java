package com.example.enterpriseapi.produtoInterface;

import com.example.enterpriseapi.domain.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesInterface extends JpaRepository<Categories, Long> {
}
