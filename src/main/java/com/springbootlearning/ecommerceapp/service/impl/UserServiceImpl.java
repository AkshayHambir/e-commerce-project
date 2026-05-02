package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.user.UserOutDTO;
import com.springbootlearning.ecommerceapp.entities.UserEntity;
import com.springbootlearning.ecommerceapp.mapper.UserMapper;
import com.springbootlearning.ecommerceapp.repositories.UserRepository;
import com.springbootlearning.ecommerceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserOutDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserOutDTO> userOutDTOs = userEntities.stream().map(userMapper::toUserOutDTO).toList();
        return userOutDTOs;
    }
}
