package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    private ArrayList<Category> categories = new ArrayList<>();

    @GetMapping("/api/public/categories")
    public List<Category> getCategories()
    {
        return categories;
    }

    @PostMapping("/api/admin/categories")
    public Category createCategory(@RequestBody Category category)
    {
        categories.add(category);
        return categories.get(categories.size() - 1);
    }
}
