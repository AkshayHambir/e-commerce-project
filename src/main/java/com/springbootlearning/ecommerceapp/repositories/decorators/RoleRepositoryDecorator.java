package com.springbootlearning.ecommerceapp.repositories.decorators;

import com.springbootlearning.ecommerceapp.entities.Role;
import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.exceptions.ResourceNotFoundException;
import com.springbootlearning.ecommerceapp.models.enums.AppRole;
import com.springbootlearning.ecommerceapp.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRepositoryDecorator {
    private final RolesRepository rolesRepository;

    public Role findByRoleName(AppRole role){
        return rolesRepository.findByRoleName(role)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "roleName", role.name()));
    }
}
