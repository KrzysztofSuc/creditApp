package com.creditApp.controller;

import com.creditApp.model.dto.ProductDto;
import com.creditApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDto));
    }

    @GetMapping("/{creditNumber}")
    public ResponseEntity<ProductDto> getProductByCreditNumber(@PathVariable String creditNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByCreditNumber(creditNumber));
    }

    @DeleteMapping("/{creditName}")
    public ResponseEntity removeProduct(@PathVariable String creditName) {
        productService.removeProduct(creditName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
