package com.springbootlearning.ecommerceapp.security.services;

import com.springbootlearning.ecommerceapp.entities.UserEntity;
import com.springbootlearning.ecommerceapp.repositories.decorators.UserRepositoryDecorator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryDecorator userRepositoryDecorator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepositoryDecorator.findByUsername(username);
        return UserDetailsImpl.build(user);
    }
}
