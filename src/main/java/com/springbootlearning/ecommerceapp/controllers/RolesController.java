package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.role.RoleInDTO;
import com.springbootlearning.ecommerceapp.dto.role.RoleOutDTO;
import com.springbootlearning.ecommerceapp.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RolesController {

    private final RoleService roleService;

    public ResponseEntity<RoleOutDTO> createRole(@Valid @RequestBody RoleInDTO roleInDTO) {
        return ResponseEntity.ok(roleService.createRole(roleInDTO));
    }
}
