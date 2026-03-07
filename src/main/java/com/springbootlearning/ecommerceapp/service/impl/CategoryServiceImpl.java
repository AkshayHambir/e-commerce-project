package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.models.Category;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public Category createCategory(Category category) {
        if(Objects.isNull(category.getCategoryId())) {
            if (categories.isEmpty()) {
                category.setCategoryId(1L);
            } else {
                Long lastId = categories.get(categories.size() - 1).getCategoryId();
                category.setCategoryId(lastId + 1);
            }
        }
        categories.add(category);
        return categories.get(categories.size() - 1);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories1 = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).toList();
        if(categories1.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }
        categories.remove(categories1.get(0));
        return "Category with id : " + categoryId + " deleted successfully";
    }
}
