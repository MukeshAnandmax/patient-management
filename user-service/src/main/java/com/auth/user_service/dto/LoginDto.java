package com.auth.user_service.dto;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
public class LoginDto {

    @NotEmpty(message = "Email can not be empty or null")
    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty(message = "Password can not be empty or null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password must contain at least one letter, one number, and one special character")
    private String password;

}
