package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    Category createCategory(Category category);

    String updateCategory(Long categoryId, Category updatedCategory);

    String deleteCategory(Long categoryId);
}
