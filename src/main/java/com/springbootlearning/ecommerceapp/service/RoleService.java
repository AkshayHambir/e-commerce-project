package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.role.RoleInDTO;
import com.springbootlearning.ecommerceapp.dto.role.RoleOutDTO;

import java.util.List;

public interface RoleService {
    RoleOutDTO createRole(RoleInDTO roleInDTO);

    List<RoleOutDTO> getAllRoles();
}
