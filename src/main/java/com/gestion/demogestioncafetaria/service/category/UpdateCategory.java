package com.gestion.demogestioncafetaria.service.category;

import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.repository.CategoryRepository;
import com.gestion.demogestioncafetaria.resource.category.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCategory {
    private final CategoryRepository categoryRepository;

    public String execute(CategoryRequest request){
        log.info("Inside UpdateCategory : {}",request);
        var category = this.categoryRepository.findById(request.id()).orElseThrow(
                ()-> new ResourceNotFoundException(CATEGORY_NOT_FOUND)
        );
        category.setName(request.name());
        this.categoryRepository.save(category);
        return "Category Updated Successfully !";
    }
}
