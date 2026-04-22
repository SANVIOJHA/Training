package com.smartcourier.auth.service;

import com.smartcourier.auth.dto.AuthRequest;
import com.smartcourier.auth.entity.User;

import java.util.List;

public interface AuthService {

    String signup(AuthRequest request);

    String login(AuthRequest request);

    List<User> getAllUsers();

    User getUserById(Long id);
}
