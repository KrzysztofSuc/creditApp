package com.creditApp.service;

import com.creditApp.model.Customer;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer findByCreditNumber(String creditNumber);

}
