package com.auth.user_service.service;


import com.auth.user_service.dto.LoginDto;
import com.auth.user_service.dto.SignupDto;
import com.auth.user_service.dto.UpdatePasswordDto;
import com.auth.user_service.dto.UserResponseDto;
import com.auth.user_service.model.User;

import java.util.List;

public interface IUserService {

    UserResponseDto saveUser(SignupDto signupDto);
    String verifyUser(LoginDto loginDto);
    UserResponseDto updatePassword(UpdatePasswordDto updatePasswordDto, String token);
    User getUserByEmail(String token);
    void softDeleteUser(String email,String token);
    List<UserResponseDto> findAllNonDeletedUsers();

}
