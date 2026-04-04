package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;
import com.springbootlearning.ecommerceapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/products")
    public ResponseEntity<ProductOutDTO> saveProduct(
            @PathVariable Long categoryId,
            @RequestBody @Valid ProductInDTO productInDTO
            ) {
        ProductOutDTO productOutDTO = productService.saveProduct(categoryId, productInDTO);
        return new ResponseEntity<>(productOutDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<PaginatedResponse<ProductOutDTO>> getCategories(
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "productId") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    )
    {
        return ResponseEntity.ok(productService.getProducts(pageNumber, pageSize, sortBy, order));
    }

}
