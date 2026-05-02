package com.springbootlearning.ecommerceapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutDTO {
    private Long userId;

    private String username;

    private String email;

    private String password;
}
