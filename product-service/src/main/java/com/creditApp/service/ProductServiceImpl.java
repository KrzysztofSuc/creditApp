package com.creditApp.service;

import com.creditApp.model.Product;
import com.creditApp.model.dto.ProductDto;
import com.creditApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return modelMapper.map(productRepository.save(modelMapper.map(productDto, Product.class)), ProductDto.class);
    }

    @Override
    public ProductDto findByCreditNumber(String creditNumber) {
        return Optional.ofNullable(productRepository.findByCreditNumber(creditNumber))
                .map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void removeProduct(String creditNumber) {
        Product product = modelMapper.map(findByCreditNumber(creditNumber), Product.class);
        productRepository.deleteByCreditNumber(product.getCreditNumber());
    }
}
