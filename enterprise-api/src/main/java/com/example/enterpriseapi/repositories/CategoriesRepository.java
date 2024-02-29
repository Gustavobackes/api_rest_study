package com.example.enterpriseapi.repositories;

import com.example.enterpriseapi.domain.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
