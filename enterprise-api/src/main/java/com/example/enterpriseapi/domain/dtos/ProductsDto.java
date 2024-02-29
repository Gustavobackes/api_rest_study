package com.example.enterpriseapi.domain.dtos;

import com.example.enterpriseapi.domain.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
public class ProductsDto {
    private Long id;
    private String nome;
    private Double valor;
    private List<CategoriesDto> categoriesDtos;
}
