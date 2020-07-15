package com.creditApp.model;

import com.creditApp.model.dto.CustomerDto;
import com.creditApp.model.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditContainer {
    @Valid
    private CustomerDto customerDto;
    @Valid
    private ProductDto productDto;
}
