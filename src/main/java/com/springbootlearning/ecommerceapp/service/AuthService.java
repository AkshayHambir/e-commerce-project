package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.auth.LoginRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.SignupRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.UserInfoResponse;
import com.springbootlearning.ecommerceapp.dto.response.MessageResponse;

public interface AuthService {
    UserInfoResponse authenticateUser(LoginRequestDTO loginRequestDTO);

    MessageResponse registerUser(SignupRequestDTO signupRequestDTO);
}
