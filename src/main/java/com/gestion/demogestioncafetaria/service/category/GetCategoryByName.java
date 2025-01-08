package com.gestion.demogestioncafetaria.service.category;

import com.gestion.demogestioncafetaria.entity.Category;
import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetCategoryByName {

    private final CategoryRepository categoryRepository;

    public Category execute(String name) {
        return this.categoryRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException(CATEGORY_NOT_FOUND)
        );
    }
}
