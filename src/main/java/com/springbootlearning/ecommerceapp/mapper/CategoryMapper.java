package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryOutDTO categoryEntityToCategoryOutDTO(CategoryEntity categoryEntity);

    @Mapping(target = "categoryId", ignore = true)
    CategoryEntity categoryInDTOToCategoryEntity(CategoryInDTO categoryInDTO);
}
