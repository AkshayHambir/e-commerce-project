package com.springbootlearning.ecommerceapp.repositories;

import com.springbootlearning.ecommerceapp.entities.CategoryEntity;
import com.springbootlearning.ecommerceapp.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByCategoryAndProductNameIgnoreCase(CategoryEntity category, String name);

    Page<ProductEntity> findByCategory(CategoryEntity category, Pageable pageable);

    Page<ProductEntity> findByProductNameContainsIgnoreCase(String productName, Pageable pageable);
}
