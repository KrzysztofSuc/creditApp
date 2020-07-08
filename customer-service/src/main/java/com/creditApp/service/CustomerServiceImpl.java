package com.creditApp.service;

import com.creditApp.model.Customer;
import com.creditApp.model.dto.CustomerDto;
import com.creditApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(modelMapper.map(customerDto, Customer.class));
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto findByCreditNumber(String creditNumber) {
        return Optional.ofNullable(customerRepository.findByCreditNumber(creditNumber))
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .orElseThrow(NullPointerException::new);
    }
}
