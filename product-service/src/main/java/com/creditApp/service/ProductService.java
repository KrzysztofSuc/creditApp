package com.creditApp.service;

import com.creditApp.model.Product;

public interface ProductService {
    Product addProduct(Product product);

    Product findByCreditNumber(String creditNumber);
}
