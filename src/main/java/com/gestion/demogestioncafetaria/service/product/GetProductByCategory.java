package com.gestion.demogestioncafetaria.service.product;

import com.gestion.demogestioncafetaria.mapper.product.Mapper;
import com.gestion.demogestioncafetaria.repository.ProductRepository;
import com.gestion.demogestioncafetaria.resource.product.ProductByCategoryResponse;
import com.gestion.demogestioncafetaria.resource.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductByCategory {

    private final ProductRepository productRepository;

    public List<ProductByCategoryResponse> execute(Long id) {
        log.info("Inside GetProductBiCategory");
        return this.productRepository.findProductByCategoryId(id).stream().map(Mapper::mapResponse).toList();
    }
}
