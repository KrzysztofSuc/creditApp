package com.creditApp.service;

import com.creditApp.model.Customer;
import com.creditApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByCreditNumber(String creditNumber) {
        return customerRepository.findByCreditNumber(creditNumber);
    }
}
