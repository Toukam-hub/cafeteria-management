package com.gestion.demogestioncafetaria.repository;

import com.gestion.demogestioncafetaria.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByCreateBy(String name);
}
