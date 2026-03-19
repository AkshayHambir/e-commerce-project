package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<CategoryOutDTO>> getCategories()
    {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryOutDTO> createCategory(@RequestBody @Valid CategoryInDTO categoryInDTO)
    {
        CategoryOutDTO savedCategory = categoryService.createCategory(categoryInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryEntity> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid CategoryEntity categoryEntity
    )
    {
        CategoryEntity updatedCategoryEntity = categoryService.updateCategory(categoryId, categoryEntity);
       return new ResponseEntity<>(updatedCategoryEntity, HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId)
    {
        categoryService.deleteCategory(categoryId);
    }
}
