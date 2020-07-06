package com.creditApp.repository;

import com.creditApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCreditNumber(String creditNumber);
}
