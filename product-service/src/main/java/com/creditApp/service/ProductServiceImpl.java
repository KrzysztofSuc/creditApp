package com.creditApp.service;

import com.creditApp.model.Product;
import com.creditApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findByCreditNumber(String creditNumber) {
        return productRepository.findByCreditNumber(creditNumber);
    }
}
