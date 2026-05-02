package com.springbootlearning.ecommerceapp.dto.role;

import com.springbootlearning.ecommerceapp.models.enums.AppRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOutDTO {
    private Long roleId;
    private AppRole roleName;
}
