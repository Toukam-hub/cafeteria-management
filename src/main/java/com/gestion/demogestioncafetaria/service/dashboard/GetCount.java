package com.gestion.demogestioncafetaria.service.dashboard;

import com.gestion.demogestioncafetaria.repository.BillRepository;
import com.gestion.demogestioncafetaria.repository.CategoryRepository;
import com.gestion.demogestioncafetaria.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetCount {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final BillRepository billRepository;

    public Map<String,Long> execute(){
        log.info("Inside getCount");
        return Map.of("Category",this.categoryRepository.count(),
                      "product", this.productRepository.count(),
                      "bill", this.billRepository.count());
    }
}
