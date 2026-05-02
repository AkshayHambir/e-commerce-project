package com.springbootlearning.ecommerceapp.service.validators;

import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.security.services.UserDetailsImpl;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthServiceValidator {
    public void validateUserDetails(UserDetailsImpl userDetails) {
        if (Objects.isNull(userDetails)) {
            throw new APIException("Error in retrieving user details.");
        }
    }
}
