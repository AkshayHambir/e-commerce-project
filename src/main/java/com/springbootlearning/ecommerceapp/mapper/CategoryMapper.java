package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryUpdateDTO;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryOutDTO categoryEntityToCategoryOutDTO(CategoryEntity categoryEntity);

    @Mapping(target = "categoryId", ignore = true)
    CategoryEntity categoryInDTOToCategoryEntity(CategoryInDTO categoryInDTO);

    @Mapping(target = "categoryId", ignore = true)
    void categoryUpdateDTOToCategoryEntity(CategoryUpdateDTO categoryUpdateDTO, @MappingTarget CategoryEntity categoryEntity);
}
