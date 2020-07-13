package com.creditApp.model;

import com.creditApp.model.dto.CustomerDto;
import com.creditApp.model.dto.ProductDto;
import lombok.Getter;

import javax.validation.Valid;

@Getter
public class CreditContainer {
    @Valid
    private CustomerDto customerDto;
    @Valid
    private ProductDto productDto;
}
