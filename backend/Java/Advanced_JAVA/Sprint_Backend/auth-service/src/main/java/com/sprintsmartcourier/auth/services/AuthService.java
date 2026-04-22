package com.sprintsmartcourier.auth.services;

import com.sprintsmartcourier.auth.dto.*;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}