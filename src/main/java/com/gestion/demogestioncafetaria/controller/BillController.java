package com.gestion.demogestioncafetaria.controller;

import com.gestion.demogestioncafetaria.resource.bill.BillRequest;
import com.gestion.demogestioncafetaria.service.bill.InserBill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bill")
public class BillController {
    private final InserBill inserBill;

    public BillController(InserBill inserBill) {
        this.inserBill = inserBill;}

    @PostMapping("/generateReport")
    public ResponseEntity<String> generateReport(@RequestBody BillRequest request){
    return ResponseEntity.ok(this.inserBill.execute(request));
    }

}
