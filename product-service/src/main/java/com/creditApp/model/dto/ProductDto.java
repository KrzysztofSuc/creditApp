package com.creditApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotEmpty(message = "{product.name.notEmpty}")
    private String name;
    @NotNull(message = "{product.value.notNull}")
    private double value;
    private String creditNumber;
}
