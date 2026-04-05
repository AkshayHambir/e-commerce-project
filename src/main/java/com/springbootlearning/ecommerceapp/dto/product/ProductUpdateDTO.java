package com.springbootlearning.ecommerceapp.dto.product;

import jakarta.validation.constraints.Min;
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
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    private String productName;

    @NotBlank
    @Size(min = 3, message = "Product description must contain at least 3 characters")
    private String description;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Min(value = 1, message = "Price must be at least 1")
    private double price;

    @Min(value = 0, message = "Discount must be at least 0")
    private double discount;
}
