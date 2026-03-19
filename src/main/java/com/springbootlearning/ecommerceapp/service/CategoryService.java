package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryOutDTO> getCategories();

    CategoryOutDTO createCategory(CategoryInDTO categoryInDTO);

    CategoryEntity updateCategory(Long categoryId, CategoryEntity updatedCategoryEntity);

    void deleteCategory(Long categoryId);
}
