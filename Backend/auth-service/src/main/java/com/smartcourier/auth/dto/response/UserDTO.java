package com.smartcourier.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User DTO — NEVER exposes password.
 * Used for all user-related API responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String phoneNumber;
    private String role;
    private boolean active;
}
