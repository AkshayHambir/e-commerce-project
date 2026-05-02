package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.auth.LoginRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.SignupRequestDTO;
import com.springbootlearning.ecommerceapp.dto.auth.UserInfoResponse;
import com.springbootlearning.ecommerceapp.dto.response.MessageResponse;
import com.springbootlearning.ecommerceapp.security.jwt.JwtUtils;
import com.springbootlearning.ecommerceapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        UserInfoResponse userInfoResponse = authService.authenticateUser(loginRequestDTO);
        return ResponseEntity.ok(userInfoResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequestDTO signupRequestDTO){
        MessageResponse messageResponse = authService.registerUser(signupRequestDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/currentUserInfo")
    public ResponseEntity<UserInfoResponse> getCurrentUserInfo(){
        UserInfoResponse userInfoResponse = authService.getCurrentUserInfo();
        return ResponseEntity.ok(userInfoResponse);
    }
}
