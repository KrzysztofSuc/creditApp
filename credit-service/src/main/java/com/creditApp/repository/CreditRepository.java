package com.creditApp.repository;

import com.creditApp.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    Credit getCreditByCreditNumber(String creditNumber);
}
