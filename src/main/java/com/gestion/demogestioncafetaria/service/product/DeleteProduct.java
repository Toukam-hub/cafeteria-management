package com.gestion.demogestioncafetaria.service.product;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.PRODUCT_NOT_FOUND;
import static com.gestion.demogestioncafetaria.constent.CafeConstent.PRODUCT_SUCCESSFULLY_DELETE;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteProduct {

    private final ProductRepository productRepository;

    public String execute(Long id) {
        log.info("Inside DeleteProduct id {}", id);
        if (!this.productRepository.existsById(id)) {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
        this.productRepository.deleteById(id);
        return PRODUCT_SUCCESSFULLY_DELETE;
    }
}
