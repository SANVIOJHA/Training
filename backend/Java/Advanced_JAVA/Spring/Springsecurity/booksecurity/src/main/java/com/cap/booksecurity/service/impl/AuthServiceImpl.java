package com.cap.booksecurity.service.impl;


import com.cap.booksecurity.dto.*;
import com.cap.booksecurity.entity.*;
import com.cap.booksecurity.repository.UserRepository;
import com.cap.booksecurity.security.JwtUtil;
import com.cap.booksecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse login(LoginRequest request) {

        var user = repo.findByUsername(request.getUsername())
                .orElseThrow();

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    @Override
    public String register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        repo.save(user);

        return "User registered";
    }
}