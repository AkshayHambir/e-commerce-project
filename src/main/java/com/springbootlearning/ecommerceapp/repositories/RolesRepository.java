package com.springbootlearning.ecommerceapp.repositories;

import com.springbootlearning.ecommerceapp.entities.Role;
import com.springbootlearning.ecommerceapp.models.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole roleName);
}
