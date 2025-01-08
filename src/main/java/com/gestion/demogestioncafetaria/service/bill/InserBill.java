package com.gestion.demogestioncafetaria.service.bill;

import com.gestion.demogestioncafetaria.mapper.bill.Mapper;
import com.gestion.demogestioncafetaria.repository.BillRepository;
import com.gestion.demogestioncafetaria.resource.bill.BillRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InserBill {

    private final BillRepository billRepository;

    public String execute(BillRequest request) {
        log.info("Inside InsertBill : {}", request);
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        this.billRepository.save(Mapper.map(request, user));
        return "";
    }
}
