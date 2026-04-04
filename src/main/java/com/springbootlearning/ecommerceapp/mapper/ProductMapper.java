package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.product.ProductInDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductOutDTO;
import com.springbootlearning.ecommerceapp.dto.product.ProductUpdateDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "productEntity.category.categoryId")
    ProductOutDTO productEntityToProductOutDTO(ProductEntity productEntity);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "image", constant = "default.png")
    @Mapping(target = "specialPrice", expression = "java(getSpecialPrice(productInDTO.getPrice(), productInDTO.getDiscount()))")
    ProductEntity productInDTOToProductEntity(ProductInDTO productInDTO, CategoryEntity category);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "specialPrice", expression = "java(getSpecialPrice(productUpdateDTO.getPrice(), productUpdateDTO.getDiscount()))")
    void productUpdateDTOToProductEntity(ProductUpdateDTO productUpdateDTO, @MappingTarget ProductEntity productEntity);

    @Mapping(target = "content", source = "productOutDTOs")
    @Mapping(target = "pageNumber", source = "page.number")
    @Mapping(target = "pageSize", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "lastPage", source = "page.last")
    PaginatedResponse<ProductOutDTO> toPaginatedResponse(Page<ProductEntity> page, List<ProductOutDTO> productOutDTOs);

    default double getSpecialPrice(double price, double discount){
        return price - ((discount * 0.01) * price);
    }
}
