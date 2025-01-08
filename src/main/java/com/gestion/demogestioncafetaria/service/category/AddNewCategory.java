package com.gestion.demogestioncafetaria.service.category;

import com.gestion.demogestioncafetaria.entity.Category;
import com.gestion.demogestioncafetaria.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddNewCategory {
    private final CategoryRepository categoryRepository;

    public String execute(Map<String, String> request) {
        log.info("Inside AddNewCategory : {}", request);
        Category category = new Category();
        category.setName(request.get("name"));
        this.categoryRepository.save(category);
        return "Category Added Successfully !";
    }
}
