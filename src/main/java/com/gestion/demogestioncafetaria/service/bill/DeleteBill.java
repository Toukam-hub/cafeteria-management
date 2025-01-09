package com.gestion.demogestioncafetaria.service.bill;

import com.gestion.demogestioncafetaria.exception.ResourceNotFoundException;
import com.gestion.demogestioncafetaria.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.BILL_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteBill {

    private final BillRepository billRepository;

    public String execute(Long id) {
        log.info("Inside delete :{}",id);
        if (!this.billRepository.existsById(id)) {
            throw new ResourceNotFoundException(BILL_NOT_FOUND);
        }
        this.billRepository.deleteById(id);
        return "Bill Deleted Successfully !";
    }
}
