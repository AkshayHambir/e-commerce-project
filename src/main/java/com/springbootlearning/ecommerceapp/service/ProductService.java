package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;

public interface ProductService {
    ProductOutDTO saveProduct(Long categoryId, ProductInDTO productInDTO);
}
