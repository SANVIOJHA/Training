package com.smartcourier.auth.dto.request;

import com.smartcourier.common.validation.StrongPassword;
import com.smartcourier.common.validation.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Request DTO for user registration.
 * Separated from LoginRequest to enforce different validation rules.
 */
@Data
public class SignupRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @ValidPhoneNumber
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @StrongPassword
    private String password;
}
