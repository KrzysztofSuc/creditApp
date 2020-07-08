package com.creditApp.controller;

import com.creditApp.model.dto.CustomerDto;
import com.creditApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.addCustomer(customerDto));
    }

    @GetMapping("/{creditNumber}")
    public ResponseEntity<CustomerDto> getCustomerByCreditNumber(@PathVariable String creditNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByCreditNumber(creditNumber));
    }
}
