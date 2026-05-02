package com.springbootlearning.ecommerceapp.service.validators;

import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.repositories.UserRepository;
import com.springbootlearning.ecommerceapp.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthServiceValidator {

    private final UserRepository userRepository;

    public void validateUserDetails(UserDetailsImpl userDetails) {
        if (Objects.isNull(userDetails)) {
            throw new APIException("Error in retrieving user details.");
        }
    }

    public void validateIfUserWithUsernameAlreadyExists(String username) {
        log.info("usernae: {}", username);
        if (userRepository.existsByUsername(username)) {
            throw new APIException("Username is already taken.");
        }
    }

    public void validateIfUserWithEmailAlreadyExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new APIException("Email is already in use.");
        }
    }
}

