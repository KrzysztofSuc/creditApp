package com.creditApp.controller;

import com.creditApp.model.Product;
import com.creditApp.model.dto.ProductDto;
import com.creditApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    public Product addProduct(ProductDto productDto) {
        return productService.addProduct(modelMapper.map(productDto, Product.class));
    }

    @GetMapping("/{creditNumber}")
    public ProductDto getProductByCreditNumber(@PathVariable String creditNumber) {
        return modelMapper.map(productService.findByCreditNumber(creditNumber), ProductDto.class);
    }
}
