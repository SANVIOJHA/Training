package com.smartcourier.auth.service.impl;

import com.smartcourier.auth.dto.request.LoginRequest;
import com.smartcourier.auth.dto.request.SignupRequest;
import com.smartcourier.auth.dto.response.AuthTokenResponse;
import com.smartcourier.auth.entity.User;
import com.smartcourier.auth.mapper.UserMapper;
import com.smartcourier.auth.repository.UserRepository;
import com.smartcourier.auth.security.JwtUtil;
import com.smartcourier.common.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private UserMapper userMapper;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(userRepository, jwtUtil, passwordEncoder, rabbitTemplate, userMapper);
    }

    @Test
    void signupShouldDefaultRoleToCustomer() {
        SignupRequest request = new SignupRequest();
        request.setUsername("alice");
        request.setPassword("Secret1!");
        request.setPhoneNumber("1234567890");

        when(userRepository.findByUsername("alice")).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber("1234567890")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("Secret1!")).thenReturn("encoded-secret");

        String result = authService.signup(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertEquals("User registered successfully", result);
        assertEquals("alice", userCaptor.getValue().getUsername());
        assertEquals("encoded-secret", userCaptor.getValue().getPassword());
        assertEquals("CUSTOMER", userCaptor.getValue().getRole());
    }

    @Test
    void loginShouldThrowWhenPasswordDoesNotMatch() {
        LoginRequest request = new LoginRequest();
        request.setUsername("alice");
        request.setPassword("wrong-password");

        User user = new User();
        user.setUsername("alice");
        user.setPassword("encoded-secret");
        user.setRole("CUSTOMER");
        user.setActive(true);

        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "encoded-secret")).thenReturn(false);

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> authService.login(request));
        assertEquals("Invalid credentials", exception.getMessage());
        verify(jwtUtil, never()).generateToken(any(), any());
    }

    @Test
    void loginShouldReturnTokensWhenCredentialsMatch() {
        LoginRequest request = new LoginRequest();
        request.setUsername("alice");
        request.setPassword("Secret1!");

        User user = new User();
        user.setUsername("alice");
        user.setPassword("encoded-secret");
        user.setRole("ADMIN");
        user.setActive(true);

        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("Secret1!", "encoded-secret")).thenReturn(true);
        when(jwtUtil.generateToken("alice", "ADMIN")).thenReturn("jwt-token");
        when(jwtUtil.generateRefreshToken("alice")).thenReturn("refresh-token");

        AuthTokenResponse response = authService.login(request);

        assertEquals("jwt-token", response.getAccessToken());
        assertEquals("refresh-token", response.getRefreshToken());
        assertEquals("alice", response.getUsername());
        assertEquals("ADMIN", response.getRole());
        verify(jwtUtil).generateToken("alice", "ADMIN");
    }
}
