package com.springbootlearning.ecommerceapp.repositories;

import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByCategoryName(String categoryName);

    CategoryEntity findByCategoryName(String categoryName);
}
