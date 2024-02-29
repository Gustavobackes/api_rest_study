package com.example.enterpriseapi.domain.dtos;

import com.example.enterpriseapi.domain.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDto {
    private Long id;
    private String tipo;
    private Integer popularidade;

    private List<ProductsDto> productsDtos;

    public CategoriesDto(Categories categories) {
        id = categories.getId();
        tipo = categories.getTipo();
        popularidade = categories.getPopularidade();
    }
}
