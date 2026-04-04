package com.springbootlearning.ecommerceapp.repositories.decorators;

import com.springbootlearning.ecommerceapp.entities.ProductEntity;
import com.springbootlearning.ecommerceapp.exceptions.ResourceNotFoundException;
import com.springbootlearning.ecommerceapp.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRepositoryDecorator {
    private final ProductRepository productRepository;

    public ProductEntity getByIdPrimary(Long id){
        return  productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", id));
    }
}
