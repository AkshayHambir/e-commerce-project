package com.springbootlearning.ecommerceapp.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {
    @NotBlank
    @Size(min = 3, message = "Product description must contain at least 3 characters")
    private String productName;

    @NotBlank
    @Size(min = 3, message = "Product description must contain at least 3 characters")
    private String description;

    private int quantity;

    private double price;

    private double discount;
}
