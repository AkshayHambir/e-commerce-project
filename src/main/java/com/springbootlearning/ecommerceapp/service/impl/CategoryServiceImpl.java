package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryUpdateDTO;
import com.springbootlearning.ecommerceapp.dto.response.PaginatedResponse;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.mapper.CategoryMapper;
import com.springbootlearning.ecommerceapp.repositories.CategoryRepository;
import com.springbootlearning.ecommerceapp.repositories.decorators.CategoryRepositoryDecorator;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final CategoryRepositoryDecorator categoryRepositoryDecorator;

    @Override
    public PaginatedResponse<CategoryOutDTO> getCategories(Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryEntity> categoryEntities = categoryPage.getContent();
        List<CategoryOutDTO> categoryOutDTOs = categoryEntities.stream().map(categoryEntity -> categoryMapper.categoryEntityToCategoryOutDTO(categoryEntity)).toList();

        return categoryMapper.toPaginatedResponse(categoryPage, categoryOutDTOs);
    }

    @Override
    public CategoryOutDTO createCategory(CategoryInDTO categoryInDTO) {
        if (categoryRepository.existsByCategoryName(categoryInDTO.getCategoryName())) {
            throw new APIException("Category with given name already exists.");
        }

        CategoryEntity categoryEntity = categoryMapper.categoryInDTOToCategoryEntity(categoryInDTO);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);

        return categoryMapper.categoryEntityToCategoryOutDTO(savedCategory);
    }

    @Override
    public CategoryOutDTO updateCategory(Long categoryId, CategoryUpdateDTO categoryUpdateDTO) {
        CategoryEntity existingCategoryEntity = categoryRepository.findByCategoryName(categoryUpdateDTO.getCategoryName());

        CategoryEntity categoryEntity = categoryRepositoryDecorator.getByIdPrimary(categoryId);

        if (Objects.nonNull(existingCategoryEntity) && !existingCategoryEntity.getCategoryId().equals(categoryId)) {
            throw new APIException("Category with given name already exists");
        }

        categoryMapper.categoryUpdateDTOToCategoryEntity(categoryUpdateDTO, categoryEntity);
        return categoryMapper.categoryEntityToCategoryOutDTO(categoryEntity);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepositoryDecorator.getByIdPrimary(categoryId);
        categoryRepository.delete(categoryEntity);
    }
}
