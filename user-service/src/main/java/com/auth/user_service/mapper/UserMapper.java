package com.auth.user_service.mapper;

import com.auth.user_service.constants.Constants;
import com.auth.user_service.dto.SignupDto;
import com.auth.user_service.dto.UserResponseDto;
import com.auth.user_service.model.Roles;
import com.auth.user_service.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class UserMapper {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User mapToUser(SignupDto signupDto, User user) {

        user.setName(signupDto.getName());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Roles roles = new Roles();
        roles.setName(Constants.USER);
        roles.setDescription(Constants.NORMAL_USER);
        user.getRoles().add(roles);

        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());

        return user;
    }

    public static UserResponseDto mapToUserResponseDto(UserResponseDto userResponseDto,User user) {

        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setCreatedAt(user.getCreatedDate());

        return userResponseDto;
    }

}
