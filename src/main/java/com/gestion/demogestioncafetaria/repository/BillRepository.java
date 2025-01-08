package com.gestion.demogestioncafetaria.repository;

import com.gestion.demogestioncafetaria.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
