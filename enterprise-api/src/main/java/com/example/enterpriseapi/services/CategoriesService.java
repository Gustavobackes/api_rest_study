package com.example.enterpriseapi.services;

import com.example.enterpriseapi.domain.dtos.CategoriesDto;
import com.example.enterpriseapi.domain.entities.Categories;
import com.example.enterpriseapi.produtoInterface.CategoriesInterface;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CategoriesService {
    private final CategoriesInterface repository;
    private final ModelMapper modelMapper;

    public CategoriesService(CategoriesInterface repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    private CategoriesDto toCategoriesDto(Categories categories) {
        return modelMapper.map(categories, CategoriesDto.class);
    }

    public ResponseEntity<Object> getAllCategories(PageRequest pageRequest) {
        Page<Object> page = repository.findAll(pageRequest).map(this::toCategoriesDto);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<Object> getCategorieById(Long categorieId) {
        boolean exist = repository.existsById(categorieId);
        if (!exist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("categoria com id " + categorieId + " não existe!");
        }
        Optional<Object> categories = repository.findById(categorieId).map(this::toCategoriesDto);

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    public ResponseEntity<Object> postNewCategorie(CategoriesDto categoriesDto) {
        Categories categories = new Categories(categoriesDto);
        return ResponseEntity.ok(repository.save(categories));
    }

    public ResponseEntity<Object> updateById(Long categorieId, CategoriesDto categoriesDto) {
        Optional<Categories> categories = repository.findById(categorieId);
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("categoria com id " + categorieId + " não existe!");
        }
        categories.get().setTipo(categoriesDto.getTipo());
        categories.get().setPopularidade(categoriesDto.getPopularidade());
        repository.save(categories.get());
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    public ResponseEntity<Object> deleteById(Long categorieId) {
        boolean exist = repository.existsById(categorieId);
        if (!exist) {
            throw new IllegalStateException("categoria com id " + categorieId + " não existe!");
        }
        repository.deleteById(categorieId);
        return ResponseEntity.status(HttpStatus.OK).body("categoria de id " + categorieId + " foi deletado!");
    }


}
