package com.springbootlearning.ecommerceapp.controllers;

import com.springbootlearning.ecommerceapp.dto.role.RoleInDTO;
import com.springbootlearning.ecommerceapp.dto.role.RoleOutDTO;
import com.springbootlearning.ecommerceapp.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RolesController {

    private final RoleService roleService;

    @PostMapping("/admin/roles")
    public ResponseEntity<RoleOutDTO> createRole(@Valid @RequestBody RoleInDTO roleInDTO) {
        return ResponseEntity.ok(roleService.createRole(roleInDTO));
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<List<RoleOutDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
