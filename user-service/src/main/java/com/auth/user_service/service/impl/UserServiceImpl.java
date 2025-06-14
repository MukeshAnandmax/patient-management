package com.auth.user_service.service.impl;

import com.auth.user_service.constants.Constants;
import com.auth.user_service.dto.LoginDto;
import com.auth.user_service.dto.SignupDto;
import com.auth.user_service.dto.UpdatePasswordDto;
import com.auth.user_service.dto.UserResponseDto;
import com.auth.user_service.exception.BadCredentialsException;
import com.auth.user_service.exception.UserAlreadyExistException;
import com.auth.user_service.exception.UserNotFoundException;
import com.auth.user_service.mapper.UserMapper;
import com.auth.user_service.model.User;
import com.auth.user_service.repository.UserRepository;
import com.auth.user_service.service.IUserService;
import com.auth.user_service.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements IUserService {


    UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public UserResponseDto saveUser(SignupDto signupDto) {

        Optional<User> byEmail = userRepository.findByEmail(signupDto.getEmail());
        if (byEmail.isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }
        User user = UserMapper.mapToUser(signupDto, new User());
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserResponseDto(new UserResponseDto(), savedUser);
    }

    @Override
    public String verifyUser(LoginDto loginDto) {
        Optional<User> byEmail = userRepository.findByEmail(loginDto.getEmail());
        if (byEmail.isEmpty() || byEmail.get().isDeleted()) {
            throw new UserNotFoundException("User Not Found with email: " + loginDto.getEmail());
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )

            );
            if(authentication.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
                return jwtUtil.generateToken(userDetails);
            }

        } catch (BadCredentialsException e) {
            return "Authentication Failed";
        }
        return "";

    }

    @Override
    public UserResponseDto updatePassword(UpdatePasswordDto updatePasswordDto, String jwtToken) {
        String email = jwtUtil.extractUsername(jwtToken.substring(7));

        if(!email.equals(updatePasswordDto.getEmail())) {
            throw new BadCredentialsException("Try again, This is not your email");
        }
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found with email: " + email));
        user.setPassword(passwordEncoder.encode(updatePasswordDto.getPassword()));
        user.setModifiedDate(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserResponseDto(new UserResponseDto(), savedUser);

    }

    @Override
    public User getUserByEmail(String jwtToken) {
        String email = jwtUtil.extractUsername(jwtToken.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found with email: " + email));
        if(!user.isDeleted()) {
            return user;
        }
        throw new UserNotFoundException("User Not Found with email: " + email);

    }

    @Override
    public void softDeleteUser(String email, String jwtToken) {

        if(email!=null){
            String emailByToken = jwtUtil.extractUsername(jwtToken.substring(7));
            User admin = userRepository.findByEmail(emailByToken).orElseThrow(() -> new UserNotFoundException("User Not Found with email: " + emailByToken));
            boolean isAdmin = admin.getRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase(Constants.SUPER_ADMIN));
            if(isAdmin){
                User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found with email: " + email));
                deleteUser(user);
            }else{
                throw new BadCredentialsException("You are not an authorized to perform this action");
            }
        }else{
            String emailByToken = jwtUtil.extractUsername(jwtToken.substring(7));
            User user = userRepository.findByEmail(emailByToken).orElseThrow(() -> new UserNotFoundException("User Not Found with email: " + emailByToken));
            deleteUser(user);
        }

    }

    private void deleteUser(User user) {
        user.setDeleted(true);
        user.setModifiedDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> findAllNonDeletedUsers() {
        List<User> byIsDeletedFalse = userRepository.findByIsDeletedFalse();
        return byIsDeletedFalse.stream().map(user -> UserMapper.mapToUserResponseDto(new UserResponseDto(), user)).toList();
    }
}
