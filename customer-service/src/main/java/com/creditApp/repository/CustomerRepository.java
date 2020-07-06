package com.creditApp.repository;

import com.creditApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCreditNumber(String creditNumber);
}
