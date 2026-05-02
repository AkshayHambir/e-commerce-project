package com.springbootlearning.ecommerceapp.service;

import com.springbootlearning.ecommerceapp.dto.user.UserOutDTO;

import java.util.List;

public interface UserService {
    List<UserOutDTO> getAllUsers();
}
