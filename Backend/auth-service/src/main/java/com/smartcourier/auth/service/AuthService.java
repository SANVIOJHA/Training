package com.smartcourier.auth.service;

import com.smartcourier.auth.dto.request.LoginRequest;
import com.smartcourier.auth.dto.request.SignupRequest;
import com.smartcourier.auth.dto.response.AuthTokenResponse;
import com.smartcourier.auth.dto.response.UserDTO;
import com.smartcourier.auth.dto.RoleUpdateRequest;
import com.smartcourier.auth.dto.UserUpdateRequest;

import java.util.List;
import java.util.Map;

public interface AuthService {

    String signup(SignupRequest request);

    AuthTokenResponse login(LoginRequest request);

    Map<String, String> refreshToken(String refreshToken);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);

    List<UserDTO> getUsersByRole(String role);

    UserDTO updateUser(Long id, UserUpdateRequest request);

    UserDTO updateUserRole(Long id, RoleUpdateRequest request);

    void deleteUser(Long id);

    long countUsers();
}
