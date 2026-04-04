package com.springbootlearning.ecommerceapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private double specialPrice;
    private double discount;
    private Long categoryId;
    private String image;
}
