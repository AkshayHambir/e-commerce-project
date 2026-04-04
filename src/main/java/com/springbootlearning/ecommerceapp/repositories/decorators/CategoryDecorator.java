package com.springbootlearning.ecommerceapp.repositories.decorators;

import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.exceptions.ResourceNotFoundException;
import com.springbootlearning.ecommerceapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDecorator {

    private final CategoryRepository categoryRepository;

    public CategoryEntity getByIdPrimary(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));
    }
}
