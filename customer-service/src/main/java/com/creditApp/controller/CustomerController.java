package com.creditApp.controller;

import com.creditApp.model.dto.CustomerDto;
import com.creditApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.addCustomer(customerDto));
    }

    @GetMapping("/{creditNumber}")
    public ResponseEntity<CustomerDto> getCustomerByCreditNumber(@PathVariable String creditNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByCreditNumber(creditNumber));
    }

    @DeleteMapping("/{creditNumber}")
    public ResponseEntity removeCustomer(@PathVariable String creditNumber) {
        customerService.removeCustomer(creditNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
