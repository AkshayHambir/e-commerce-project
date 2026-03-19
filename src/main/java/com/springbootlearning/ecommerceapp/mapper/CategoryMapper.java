package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryOutDTO categoryEntityToCategoryOutDTO(CategoryEntity categoryEntity);
}
