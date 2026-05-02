package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.user.UserOutDTO;
import com.springbootlearning.ecommerceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserOutDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
