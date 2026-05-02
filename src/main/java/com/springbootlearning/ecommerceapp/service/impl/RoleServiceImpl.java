package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.role.RoleInDTO;
import com.springbootlearning.ecommerceapp.dto.role.RoleOutDTO;
import com.springbootlearning.ecommerceapp.entities.Role;
import com.springbootlearning.ecommerceapp.mapper.RoleMapper;
import com.springbootlearning.ecommerceapp.repositories.RolesRepository;
import com.springbootlearning.ecommerceapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    private final RolesRepository rolesRepository;

    @Override
    public RoleOutDTO createRole(RoleInDTO roleInDTO) {
        Role role = roleMapper.toEntity(roleInDTO);
        Role savedRole = rolesRepository.save(role);
        return roleMapper.toOutDTO(savedRole);
    }
}
