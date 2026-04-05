package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryUpdateDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<PaginatedResponse<CategoryOutDTO>> getCategories(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "categoryId") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        return ResponseEntity.ok(categoryService.getCategories(pageNumber, pageSize, sortBy, order));
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryOutDTO> createCategory(@RequestBody @Valid CategoryInDTO categoryInDTO) {
        CategoryOutDTO savedCategory = categoryService.createCategory(categoryInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryOutDTO> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid CategoryUpdateDTO categoryUpdateDTO
    ) {
        CategoryOutDTO updatedCategory = categoryService.updateCategory(categoryId, categoryUpdateDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
