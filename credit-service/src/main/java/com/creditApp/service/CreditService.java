package com.creditApp.service;

import com.creditApp.model.CreditContainer;
import com.creditApp.model.dto.CreditDto;
import com.creditApp.model.dto.CustomerDto;
import com.creditApp.model.dto.ProductDto;

public interface CreditService {
    CreditDto addCredit(CustomerDto customerDto, ProductDto productDto);

    CreditContainer getCredit(String creditNumber);

    void removeCredit(String creditNumber);
}
