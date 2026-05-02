package com.springbootlearning.ecommerceapp.dto.role;

import com.springbootlearning.ecommerceapp.models.enums.AppRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleInDTO {

    @NotNull(message = "Role name cannot be null")
    private AppRole roleName;
}
