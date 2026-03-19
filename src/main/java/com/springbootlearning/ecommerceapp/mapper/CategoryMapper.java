package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryUpdateDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryOutDTO categoryEntityToCategoryOutDTO(CategoryEntity categoryEntity);

    @Mapping(target = "categoryId", ignore = true)
    CategoryEntity categoryInDTOToCategoryEntity(CategoryInDTO categoryInDTO);

    @Mapping(target = "categoryId", ignore = true)
    void categoryUpdateDTOToCategoryEntity(CategoryUpdateDTO categoryUpdateDTO, @MappingTarget CategoryEntity categoryEntity);

    @Mapping(target = "content", source = "categoryOutDTOs")
    @Mapping(target = "pageNumber", source = "page.number")
    @Mapping(target = "pageSize", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "lastPage", source = "page.last")
    PaginatedResponse<CategoryOutDTO> toPaginatedResponse(Page<CategoryEntity> page, List<CategoryOutDTO> categoryOutDTOs);
}
