package com.example.enterpriseapi.domain.entities;

import com.example.enterpriseapi.domain.dtos.ProductsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nome;
    @Column
    private Double valor;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private List<Categories> categories;

    public Products(ProductsDto productsDto) {
        id = productsDto.getId();
        nome = productsDto.getNome();
        valor = productsDto.getValor();
    }
}
