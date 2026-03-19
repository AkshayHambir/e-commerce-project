package com.springbootlearning.ecommerceapp.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInDTO {
    @NotBlank
    @Size(min = 3, message = "Category name must contain at least 3 characters")
    private String categoryName;
}
