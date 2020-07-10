package com.creditApp.repository;

import com.creditApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCreditNumber(String creditNumber);

    @Modifying
    @Transactional
    @Query("delete from Product p where p.creditNumber = ?1")
    void deleteByCreditNumber(String creditNumber);
}
