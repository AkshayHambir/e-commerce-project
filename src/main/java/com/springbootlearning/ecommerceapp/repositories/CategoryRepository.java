package com.springbootlearning.ecommerceapp.repositories;

import com.springbootlearning.ecommerceapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(String categoryName);

    Category findByCategoryName(String categoryName);
}
