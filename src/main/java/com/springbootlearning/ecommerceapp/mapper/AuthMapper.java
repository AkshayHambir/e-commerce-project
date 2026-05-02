package com.springbootlearning.ecommerceapp.mapper;

import com.springbootlearning.ecommerceapp.dto.auth.UserInfoResponse;
import com.springbootlearning.ecommerceapp.security.services.UserDetailsImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "id", source = "userDetails.id")
    @Mapping(target = "username", source = "userDetails.username")
    @Mapping(target = "jwtToken", source = "jwtToken")
    @Mapping(target = "roles", source = "roles")
    UserInfoResponse toUserInfoResponse(UserDetailsImpl userDetails, String jwtToken, List<String> roles);
}
