package com.springbootlearning.ecommerceapp.service.validators;

import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.repositories.CategoryRepository;
import com.springbootlearning.ecommerceapp.repositories.decorators.CategoryRepositoryDecorator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryServiceValidator {

    private final CategoryRepository categoryRepository;

    private final CategoryRepositoryDecorator categoryRepositoryDecorator;

    public void validateIfCategoryWithDuplicateNameNotPresent(String categoryName){
        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryName)) {
            throw new APIException("Category with given name already exists.");
        }
    }
}
