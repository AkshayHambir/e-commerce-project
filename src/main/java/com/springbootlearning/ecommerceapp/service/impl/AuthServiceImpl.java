package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.auth.LoginRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.SignupRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.UserInfoResponse;
import com.springbootlearning.ecommerceapp.dto.response.MessageResponse;
import com.springbootlearning.ecommerceapp.entities.Role;
import com.springbootlearning.ecommerceapp.entities.UserEntity;
import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.mapper.AuthMapper;
import com.springbootlearning.ecommerceapp.mapper.UserMapper;
import com.springbootlearning.ecommerceapp.models.enums.AppRole;
import com.springbootlearning.ecommerceapp.repositories.UserRepository;
import com.springbootlearning.ecommerceapp.repositories.decorators.RoleRepositoryDecorator;
import com.springbootlearning.ecommerceapp.security.jwt.JwtUtils;
import com.springbootlearning.ecommerceapp.security.services.UserDetailsImpl;
import com.springbootlearning.ecommerceapp.service.AuthService;
import com.springbootlearning.ecommerceapp.service.validators.AuthServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepositoryDecorator roleRepositoryDecorator;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final AuthServiceValidator authServiceValidator;

    private final AuthMapper authMapper;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfoResponse authenticateUser(LoginRequestDTO loginRequestDTO) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new APIException("Bad Credentials");
        }

        // store authentication object in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        authServiceValidator.validateUserDetails(userDetails);
        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return authMapper.toUserInfoResponse(userDetails, jwtToken, roles);
    }

    @Override
    public MessageResponse registerUser(SignupRequestDTO signupRequestDTO) {
        authServiceValidator.validateIfUserWithUsernameAlreadyExists(signupRequestDTO.getUsername());
        authServiceValidator.validateIfUserWithEmailAlreadyExists(signupRequestDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());

        Set<String> strRoles = signupRequestDTO.getRoles();
        Set<Role> roles = getRoles(strRoles);

        UserEntity userEntity = userMapper.toEntity(signupRequestDTO, encodedPassword, roles);
        userRepository.save(userEntity);
        return new MessageResponse("User registered successfully!");
    }

    private Set<Role> getRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if(strRoles == null || strRoles.isEmpty()){
            roles.add(roleRepositoryDecorator.findByRoleName(AppRole.ROLE_USER));
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin": {
                        roles.add(roleRepositoryDecorator.findByRoleName(AppRole.ROLE_ADMIN));
                        break;
                    }
                    case "seller": {
                        roles.add(roleRepositoryDecorator.findByRoleName(AppRole.ROLE_SELLER));
                        break;
                    }
                    case "user": {
                        roles.add(roleRepositoryDecorator.findByRoleName(AppRole.ROLE_USER));
                        break;
                    }
                    default:{
                        throw new APIException("Invalid role: " + role);
                    }
                }
            });
        }
        return roles;
    }
}
