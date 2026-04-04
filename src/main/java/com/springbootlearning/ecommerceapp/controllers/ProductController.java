package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;
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
}
