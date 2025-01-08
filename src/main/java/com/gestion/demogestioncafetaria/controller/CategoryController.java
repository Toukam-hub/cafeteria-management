package com.gestion.demogestioncafetaria.controller;

import com.gestion.demogestioncafetaria.resource.category.CategoryRequest;
import com.gestion.demogestioncafetaria.resource.category.CategoryResponse;
import com.gestion.demogestioncafetaria.service.category.AddNewCategory;
import com.gestion.demogestioncafetaria.service.category.GetAllCategories;
import com.gestion.demogestioncafetaria.service.category.UpdateCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final AddNewCategory addNewCategory;
    private final GetAllCategories getAllCategories;
    private final UpdateCategory updateCategory;

    public CategoryController(AddNewCategory addNewCategory, GetAllCategories getAllCategories, UpdateCategory updateCategory) {
        this.addNewCategory = addNewCategory;
        this.getAllCategories = getAllCategories;
        this.updateCategory = updateCategory;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> addNewCategory(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(addNewCategory.execute(request));
    }

    @GetMapping("/get")
    ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam(required = false) String filterValue){
        return ResponseEntity.ok(this.getAllCategories.execute(filterValue));
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<String> updateCategory(@RequestBody CategoryRequest request) {
       return ResponseEntity.ok(this.updateCategory.execute(request));
    }

}

