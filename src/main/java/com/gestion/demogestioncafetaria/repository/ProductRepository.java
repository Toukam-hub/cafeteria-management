package com.gestion.demogestioncafetaria.repository;

import com.gestion.demogestioncafetaria.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Product SET status =:status WHERE id=:id")
    Integer updateProductByStatus(@Param("status") String status, @Param("id") Long id);

    List<Product> findProductByCategoryId(Long id);

}
