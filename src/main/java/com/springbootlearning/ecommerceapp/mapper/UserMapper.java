package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.auth.SignupRequestDTO;
import com.springbootlearning.ecommerceapp.entities.Role;
import com.springbootlearning.ecommerceapp.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "password", source = "encodedPassword")
    @Mapping(target = "username", source = "signupRequestDTO.username")
    @Mapping(target = "email", source = "signupRequestDTO.email")
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    UserEntity toEntity(SignupRequestDTO signupRequestDTO, String encodedPassword, Set<Role> roles);
}
