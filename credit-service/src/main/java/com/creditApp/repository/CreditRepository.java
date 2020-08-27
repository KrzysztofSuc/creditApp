package com.creditApp.repository;

import com.creditApp.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CreditRepository extends JpaRepository<Credit, Long> {
   Optional<Credit> getCreditByCreditNumber(String creditNumber);

    @Modifying
    @Transactional
    @Query("delete from Credit c where c.creditNumber = ?1")
    void deleteByCreditNumber(String creditNumber);
}
