package com.smartcourier.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auth response containing access and refresh tokens.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String username;
    private String role;
}
