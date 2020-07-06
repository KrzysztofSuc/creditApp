package com.creditApp.controller;

import com.creditApp.model.Customer;
import com.creditApp.model.dto.CustomerDto;
import com.creditApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    public Customer addCustomer(CustomerDto customerDto){
        return customerService.addCustomer(modelMapper.map(customerDto, Customer.class));
    }

    @GetMapping("/{creditNumber}")
    public CustomerDto getCustomerByCreditNumber(@PathVariable String creditNumber){
        return modelMapper.map(customerService.findByCreditNumber(creditNumber), CustomerDto.class);
    }
}
