package com.springbootlearning.ecommerceapp.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryOutDTO {
    private Long categoryId;
    private String categoryName;
}
