package com.example.enterpriseapi.domain.entities;

import com.example.enterpriseapi.domain.dtos.CategoriesDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String tipo;
    @Column
    private Integer popularidade;

    @ManyToMany(mappedBy = "categories")
    private List<Products> products;

    public Categories(CategoriesDto categoriesDto) {
        id = categoriesDto.getId();
        tipo = categoriesDto.getTipo();
        popularidade = categoriesDto.getPopularidade();
    }
}
