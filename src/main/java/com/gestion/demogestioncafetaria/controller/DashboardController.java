package com.gestion.demogestioncafetaria.controller;

import com.gestion.demogestioncafetaria.service.dashboard.GetCount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    private final GetCount getCount;

    public DashboardController(GetCount getCount) {this.getCount = getCount;}

    @GetMapping("/details")
    ResponseEntity<Map<String,Long>> getCount(){
         return ResponseEntity.of(Optional.ofNullable(this.getCount.execute()));
     }

}
