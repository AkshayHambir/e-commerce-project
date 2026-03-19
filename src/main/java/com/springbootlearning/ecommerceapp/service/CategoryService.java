package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryUpdateDTO;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryOutDTO> getCategories();

    CategoryOutDTO createCategory(CategoryInDTO categoryInDTO);

    CategoryOutDTO updateCategory(Long categoryId, CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(Long categoryId);
}
