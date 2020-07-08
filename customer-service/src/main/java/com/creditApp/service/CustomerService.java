package com.creditApp.service;

import com.creditApp.model.dto.CustomerDto;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);

    CustomerDto findByCreditNumber(String creditNumber);

}
