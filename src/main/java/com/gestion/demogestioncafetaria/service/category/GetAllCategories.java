package com.gestion.demogestioncafetaria.service.category;

import com.gestion.demogestioncafetaria.mapper.category.Mapper;
import com.gestion.demogestioncafetaria.repository.CategoryRepository;
import com.gestion.demogestioncafetaria.resource.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCategories {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> execute(String filterValue) {
        if (filterValue != null && filterValue.equalsIgnoreCase("available")){
            return  this.categoryRepository.findCategoriesByProductStatus(filterValue).stream().map(Mapper::map).toList();
        }
        return this.categoryRepository.findAll().stream().map(Mapper::map).toList();
    }
}
