package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.exceptions.ResourceNotFoundException;
import com.springbootlearning.ecommerceapp.models.Category;
import com.springbootlearning.ecommerceapp.repositories.CategoryRepository;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        if(categoryRepository.existsByCategoryName(category.getCategoryName())){
            throw new APIException("Category with given name already exists.");
        }
            return categoryRepository.save(category);
    }

    @Override
    public String updateCategory(Long categoryId, Category updatedCategory) {
        Category existingCategory = categoryRepository.findByCategoryName(updatedCategory.getCategoryName());
        if(Objects.nonNull(existingCategory) && !existingCategory.getCategoryId().equals(categoryId)){
            throw new APIException("Category with given name already exists");
        }
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category category = categoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        category.setCategoryName(updatedCategory.getCategoryName());
        categoryRepository.save(category);

        return "Category with id: " + categoryId + " updated successfully";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category category = categoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);

        return "Category with id : " + categoryId + " deleted successfully";
    }
}
