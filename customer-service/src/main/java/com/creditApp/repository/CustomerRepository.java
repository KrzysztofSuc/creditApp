package com.creditApp.repository;

import com.creditApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCreditNumber(String creditNumber);

    @Modifying
    @Transactional
    @Query("delete from Customer c where c.creditNumber = ?1")
    void deleteByCreditNumber(String creditNumber);
}
