package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.models.Category;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import org.springframework.stereotype.Service;

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
}
