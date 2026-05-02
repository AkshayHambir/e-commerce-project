package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.role.RoleInDTO;
import com.springbootlearning.ecommerceapp.dto.role.RoleOutDTO;
import com.springbootlearning.ecommerceapp.entities.Role;
import com.springbootlearning.ecommerceapp.models.enums.AppRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "roleId", ignore = true)
    Role toEntity(RoleInDTO roleInDTO);

    RoleOutDTO toOutDTO(Role role);
}
