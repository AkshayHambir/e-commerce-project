package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.category.CategoryInDTO;
import com.springbootlearning.ecommerceapp.dto.category.CategoryOutDTO;
import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.exceptions.ResourceNotFoundException;
import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.mapper.CategoryMapper;
import com.springbootlearning.ecommerceapp.repositories.CategoryRepository;
import com.springbootlearning.ecommerceapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryOutDTO> getCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        return categoryEntities.stream().map(categoryEntity -> categoryMapper.categoryEntityToCategoryOutDTO(categoryEntity)).toList();
    }

    @Override
    public CategoryOutDTO createCategory(CategoryInDTO categoryInDTO) {
        if(categoryRepository.existsByCategoryName(categoryInDTO.getCategoryName())){
            throw new APIException("Category with given name already exists.");
        }

        CategoryEntity categoryEntity = categoryMapper.categoryInDTOToCategoryEntity(categoryInDTO);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);

        return categoryMapper.categoryEntityToCategoryOutDTO(savedCategory);
    }

    @Override
    public CategoryEntity updateCategory(Long categoryId, CategoryEntity updatedCategoryEntity) {
        CategoryEntity existingCategoryEntity = categoryRepository.findByCategoryName(updatedCategoryEntity.getCategoryName());

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
        CategoryEntity categoryEntity = categoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        if(Objects.nonNull(existingCategoryEntity) && !existingCategoryEntity.getCategoryId().equals(categoryId)){
            throw new APIException("Category with given name already exists");
        }

        categoryEntity.setCategoryName(updatedCategoryEntity.getCategoryName());
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
        CategoryEntity categoryEntity = categoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(categoryEntity);
    }
}
