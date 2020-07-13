package com.creditApp.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotEmpty(message = "{product.name.notempty}")
    private String name;
    @NotNull(message = "{product.value.notnull}")
    private double value;
    private String creditNumber;
}
