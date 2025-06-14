package com.auth.user_service.service.impl;

import com.auth.user_service.exception.UserNotFoundException;
import com.auth.user_service.model.User;
import com.auth.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user  = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with email: " + username));
        user.getRoles().size();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(role ->new SimpleGrantedAuthority(role.getName())).toList());


    }
}
