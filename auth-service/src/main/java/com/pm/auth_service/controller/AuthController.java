package com.pm.auth_service.controller;

import com.pm.auth_service.dto.LoginRequestDTO;
import com.pm.auth_service.dto.LoginResponseDTO;
import com.pm.auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Generate token", description = "Generates a token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        log.info("Login request: {}", loginRequestDTO);
        Optional<String> token = authService.authenticate(loginRequestDTO);
        log.info("Token: {}", token);
        return token.map(s -> ResponseEntity.ok(new LoginResponseDTO(s))).orElseGet(() -> ResponseEntity.status(HTTP_UNAUTHORIZED).build());

    }

}
