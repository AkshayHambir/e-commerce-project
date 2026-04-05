package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductUpdateDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductOutDTO saveProduct(Long categoryId, ProductInDTO productInDTO);

    PaginatedResponse<ProductOutDTO> getProducts(Integer pageNumber, Integer pageSize, String sortBy, String order);

    PaginatedResponse<ProductOutDTO> getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String order);

    PaginatedResponse<ProductOutDTO> searchProductsByName(String productName, Integer pageNumber, Integer pageSize, String sortBy, String order);

    ProductOutDTO findProductById(Long productId);

    ProductOutDTO updateProduct(Long categoryId, Long productId, ProductUpdateDTO productUpdateDTO);

    ProductOutDTO updateProductImage(Long productId, MultipartFile imageFile);

    void deleteProduct(Long productId);
}
