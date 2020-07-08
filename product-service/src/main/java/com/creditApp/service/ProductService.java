package com.creditApp.service;

import com.creditApp.model.dto.ProductDto;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);

    ProductDto findByCreditNumber(String creditNumber);
}
