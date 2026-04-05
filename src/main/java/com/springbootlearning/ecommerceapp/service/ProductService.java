package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductUpdateDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;

public interface ProductService {
    ProductOutDTO saveProduct(Long categoryId, ProductInDTO productInDTO);

    PaginatedResponse<ProductOutDTO> getProducts(Integer pageNumber, Integer pageSize, String sortBy, String order);

    PaginatedResponse<ProductOutDTO> getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String order);

    PaginatedResponse<ProductOutDTO> searchProductsByName(String productName, Integer pageNumber, Integer pageSize, String sortBy, String order);

    ProductOutDTO updateProduct(Long categoryId, Long productId, ProductUpdateDTO productUpdateDTO);
}
