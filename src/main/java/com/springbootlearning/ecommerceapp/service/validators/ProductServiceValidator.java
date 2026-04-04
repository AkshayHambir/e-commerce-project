package com.springbootlearning.ecommerceapp.service.validators;

import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.repositories.ProductRepository;
import com.springbootlearning.ecommerceapp.repositories.decorators.ProductRepositoryDecorator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceValidator {

    private final ProductRepository productRepository;

    private final ProductRepositoryDecorator productRepositoryDecorator;

    public void validateNoDuplicateProductNamePresent(String name){
        if(productRepository.existsByProductNameIgnoreCase(name)){
            throw new APIException("Product with given name already exists");
        }
    }
}
