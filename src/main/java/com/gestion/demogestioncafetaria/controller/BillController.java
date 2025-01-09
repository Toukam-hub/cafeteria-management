package com.gestion.demogestioncafetaria.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestion.demogestioncafetaria.resource.bill.BillPfdRequest;
import com.gestion.demogestioncafetaria.resource.bill.BillRequest;
import com.gestion.demogestioncafetaria.resource.bill.BillResponse;
import com.gestion.demogestioncafetaria.service.bill.DeleteBill;
import com.gestion.demogestioncafetaria.service.bill.GetBillByUserName;
import com.gestion.demogestioncafetaria.service.bill.GetPdf;
import com.gestion.demogestioncafetaria.service.bill.InserBill;
import com.itextpdf.text.DocumentException;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("bill")
public class BillController {

    private final InserBill inserBill;
    private final GetBillByUserName getBillByUserName;
    private final GetPdf getPdf;
    private final DeleteBill deleteBill;

    public BillController(InserBill inserBill, GetBillByUserName getBillByUserName, GetPdf getPdf, DeleteBill deleteBill) {
        this.inserBill = inserBill;
        this.getBillByUserName = getBillByUserName;
        this.getPdf = getPdf;
        this.deleteBill = deleteBill;
    }

    @PostMapping("/generateReport")
    public ResponseEntity<String> generateReport(@RequestBody BillRequest request) throws DocumentException, FileNotFoundException, JSONException, JsonProcessingException {
        return ResponseEntity.ok(this.inserBill.execute(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<BillResponse>> getBillByUserName() {
        return ResponseEntity.ok(this.getBillByUserName.execute());
    }

    @PostMapping("/getPdf")
    public ResponseEntity<byte[]> getPdf(@RequestBody BillPfdRequest request) throws DocumentException, JSONException, IOException {
        return ResponseEntity.ok(this.getPdf.execute(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> geleteBill(@PathVariable Long id){
        return ResponseEntity.ok(this.deleteBill.execute(id));
    }

}
