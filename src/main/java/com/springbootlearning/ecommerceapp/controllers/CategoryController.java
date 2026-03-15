package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.models.Category;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getCategories()
    {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category)
    {
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid Category category
    )
    {
       try {
           String status = categoryService.updateCategory(categoryId, category);
           return ResponseEntity.ok(status);
       } catch (ResponseStatusException e){
           return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
       }
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        try{
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
