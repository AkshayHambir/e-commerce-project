package com.springbootlearning.ecommerceapp.service.impl;

import com.springbootlearning.ecommerceapp.dto.auth.LoginRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.UserInfoResponse;
import com.springbootlearning.ecommerceapp.exceptions.APIException;
import com.springbootlearning.ecommerceapp.mapper.AuthMapper;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final AuthServiceValidator authServiceValidator;

    private final AuthMapper authMapper;

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
}
