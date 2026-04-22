package com.cap.booksecurity.service;

import com.cap.booksecurity.dto.*;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    String register(RegisterRequest request);
}