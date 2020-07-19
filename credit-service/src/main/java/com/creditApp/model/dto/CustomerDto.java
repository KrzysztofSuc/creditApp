package com.creditApp.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotEmpty(message = "{customer.firstName.notEmpty}")
    private String firstName;
    @NotEmpty(message = "{customer.lastName.notEmpty}")
    private String lastName;
    @NotEmpty
    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    @Size(min = 11, max = 11)
    private String pesel;
    private String creditNumber;
}
