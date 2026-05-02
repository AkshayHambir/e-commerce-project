package com.springbootlearning.ecommerceapp.repositories.decorators;

import com.springbootlearning.ecommerceapp.entities.UserEntity;
import com.springbootlearning.ecommerceapp.exceptions.ResourceNotFoundException;
import com.springbootlearning.ecommerceapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryDecorator {
    private final UserRepository userRepository;

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
}
