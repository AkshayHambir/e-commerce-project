package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.auth.LoginRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.UserInfoResponse;

public interface AuthService {
    UserInfoResponse authenticateUser(LoginRequestDTO loginRequestDTO);
}
