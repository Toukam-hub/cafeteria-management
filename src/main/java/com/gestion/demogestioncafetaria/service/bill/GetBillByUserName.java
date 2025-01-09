package com.gestion.demogestioncafetaria.service.bill;

import com.gestion.demogestioncafetaria.mapper.bill.Mapper;
import com.gestion.demogestioncafetaria.repository.BillRepository;
import com.gestion.demogestioncafetaria.resource.bill.BillResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBillByUserName {

    private final BillRepository billRepository;

    public List<BillResponse> execute() {
        log.info("Inside GetBillByUserName");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return this.billRepository.findAll().stream().map(Mapper::map).toList();
        } else
            return this.billRepository.findByCreateBy(authentication.getName()).stream().map(Mapper::map).toList();
    }
}
