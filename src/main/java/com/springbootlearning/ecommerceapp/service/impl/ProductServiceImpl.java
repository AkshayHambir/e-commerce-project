package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.entities.ProductEntity;
import com.springbootlearning.ecommerceapp.mapper.ProductMapper;
import com.springbootlearning.ecommerceapp.repositories.CategoryRepository;
import com.springbootlearning.ecommerceapp.repositories.ProductRepository;
import com.springbootlearning.ecommerceapp.repositories.decorators.CategoryRepositoryDecorator;
import com.springbootlearning.ecommerceapp.repositories.decorators.ProductRepositoryDecorator;
import com.springbootlearning.ecommerceapp.service.ProductService;
import com.springbootlearning.ecommerceapp.service.validators.ProductServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final CategoryRepositoryDecorator categoryRepositoryDecorator;

    private final ProductRepositoryDecorator productRepositoryDecorator;

    private final ProductServiceValidator productServiceValidator;

    private final ProductMapper productMapper;

    @Override
    public ProductOutDTO saveProduct(Long categoryId, ProductInDTO productInDTO) {
        CategoryEntity category = categoryRepositoryDecorator.getByIdPrimary(categoryId);
        productServiceValidator.validateNoDuplicateProductNamePresent(productInDTO.getProductName());

        ProductEntity productEntity = productMapper.productInDTOToProductEntity(productInDTO, category);
        ProductEntity savedProductEntity = productRepository.save(productEntity);

        return productMapper.productEntityToProductOutDTO(savedProductEntity);
    }
}
