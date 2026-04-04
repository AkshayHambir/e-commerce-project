package com.springbootlearning.ecommerceapp.repositories;

import com.springbootlearning.ecommerceapp.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
