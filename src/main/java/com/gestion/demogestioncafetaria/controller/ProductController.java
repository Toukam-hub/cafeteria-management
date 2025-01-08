package com.gestion.demogestioncafetaria.controller;

import com.gestion.demogestioncafetaria.resource.product.ProductRequest;
import com.gestion.demogestioncafetaria.resource.product.ProductResponse;
import com.gestion.demogestioncafetaria.resource.product.UpdateStatusRequest;
import com.gestion.demogestioncafetaria.service.product.*;
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
    private final DeleteProduct deleteProduct;
    private final GetProductById getProductById;
    private final UpdateStatusProduct updateStatusProduct;

    public ProductController(CreateProduct createProduct,
                             GetAllProduct getAllProduct,
                             UpdateProduct updateProduct,
                             DeleteProduct deleteProduct,
                             GetProductById getProductById,
                             UpdateStatusProduct updateStatusProduct) {
        this.createProduct = createProduct;
        this.getAllProduct = getAllProduct;
        this.updateProduct = updateProduct;
        this.deleteProduct = deleteProduct;
        this.getProductById = getProductById;
        this.updateStatusProduct = updateStatusProduct;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addNewProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(this.createProduct.execute(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(this.getAllProduct.execute());
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(this.updateProduct.execute(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> geleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.deleteProduct.execute(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(this.getProductById.execute(id));
    }

    @PatchMapping("/update-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updetaStaus(@RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(this.updateStatusProduct.execute(request));
    }
}
