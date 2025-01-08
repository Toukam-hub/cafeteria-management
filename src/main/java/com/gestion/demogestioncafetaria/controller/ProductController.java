package com.gestion.demogestioncafetaria.controller;

import com.gestion.demogestioncafetaria.resource.product.ProductRequest;
import com.gestion.demogestioncafetaria.resource.product.ProductResponse;
import com.gestion.demogestioncafetaria.service.product.CreateProduct;
import com.gestion.demogestioncafetaria.service.product.GetAllProduct;
import com.gestion.demogestioncafetaria.service.product.UpdateProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final CreateProduct createProduct;
    private final GetAllProduct getAllProduct;
    private final UpdateProduct updateProduct;

    public ProductController(CreateProduct createProduct, GetAllProduct getAllProduct, UpdateProduct updateProduct) {
        this.createProduct = createProduct;
        this.getAllProduct = getAllProduct;
        this.updateProduct = updateProduct;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addNewProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(this.createProduct.execute(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok(this.getAllProduct.execute());
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(this.updateProduct.execute(id,request));
    }
}
