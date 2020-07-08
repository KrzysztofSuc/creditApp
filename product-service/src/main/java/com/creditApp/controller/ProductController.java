package com.creditApp.controller;

import com.creditApp.model.dto.ProductDto;
import com.creditApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDto));
    }

    @GetMapping("/{creditNumber}")
    public ResponseEntity<ProductDto> getProductByCreditNumber(@PathVariable String creditNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByCreditNumber(creditNumber));
    }
}
