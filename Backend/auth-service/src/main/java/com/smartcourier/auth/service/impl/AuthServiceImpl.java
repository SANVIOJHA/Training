package com.smartcourier.auth.service.impl;

import com.smartcourier.auth.dto.request.LoginRequest;
import com.smartcourier.auth.dto.request.SignupRequest;
import com.smartcourier.auth.dto.response.AuthTokenResponse;
import com.smartcourier.auth.dto.response.UserDTO;
import com.smartcourier.auth.dto.RoleUpdateRequest;
import com.smartcourier.auth.dto.UserUpdateRequest;
import com.smartcourier.auth.entity.User;
import com.smartcourier.auth.mapper.UserMapper;
import com.smartcourier.auth.repository.UserRepository;
import com.smartcourier.auth.security.JwtUtil;
import com.smartcourier.auth.service.AuthService;
import com.smartcourier.common.constant.SmartCourierConstants;
import com.smartcourier.common.event.UserRegisteredEvent;
import com.smartcourier.common.exception.DuplicateResourceException;
import com.smartcourier.common.exception.ResourceNotFoundException;
import com.smartcourier.common.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RabbitTemplate rabbitTemplate;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository repo, JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder, RabbitTemplate rabbitTemplate,
                           UserMapper userMapper) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.rabbitTemplate = rabbitTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public String signup(SignupRequest request) {
        // Duplicate username check
        repo.findByUsername(request.getUsername()).ifPresent(user -> {
            throw new DuplicateResourceException("An account with this username already exists.");
        });

        // Duplicate phone check (if provided)
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isBlank()) {
            repo.findByPhoneNumber(request.getPhoneNumber()).ifPresent(user -> {
                throw new DuplicateResourceException("An account with this phone number already exists.");
            });
        }

        User user = new User();
        user.setUsername(request.getUsername().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(SmartCourierConstants.ROLE_CUSTOMER);
        user.setActive(true);

        repo.save(user);
        log.info("User registered: {}", user.getUsername());

        // Publish to RabbitMQ for SMS notification
        try {
            UserRegisteredEvent event = new UserRegisteredEvent(
                    user.getUsername(),
                    user.getPhoneNumber(),
                    user.getRole(),
                    LocalDateTime.now()
            );
            rabbitTemplate.convertAndSend(
                    SmartCourierConstants.AUTH_EXCHANGE,
                    SmartCourierConstants.USER_REGISTERED_ROUTING_KEY,
                    event
            );
        } catch (Exception e) {
            log.warn("Failed to publish user registered event: {}", e.getMessage());
        }

        return "User registered successfully";
    }

    @Override
    public AuthTokenResponse login(LoginRequest request) {
        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        if (!user.isActive()) {
            throw new UnauthorizedException("Account is deactivated");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        log.info("User logged in: {}", user.getUsername());

        return AuthTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public Map<String, String> refreshToken(String refreshToken) {
        if (!jwtUtil.isRefreshToken(refreshToken)) {
            throw new UnauthorizedException("Invalid refresh token");
        }

        String username = jwtUtil.extractUsername(refreshToken);
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("User not found"));

        return jwtUtil.refreshAccessToken(refreshToken, user.getRole());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.toDtoList(repo.findAll());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
        return userMapper.toDtoList(repo.findByRoleIgnoreCase(role));
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            user.setUsername(request.getUsername().trim());
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRole() != null && !request.getRole().isBlank()) {
            user.setRole(request.getRole().trim().toUpperCase(Locale.ROOT));
        }

        return userMapper.toDto(repo.save(user));
    }

    @Override
    public UserDTO updateUserRole(Long id, RoleUpdateRequest request) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
        user.setRole(request.getRole().trim().toUpperCase(Locale.ROOT));
        return userMapper.toDto(repo.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
        repo.delete(user);
        log.info("User deleted: {}", user.getUsername());
    }

    @Override
    public long countUsers() {
        return repo.count();
    }
}
