package com.smartcourier.auth.controller;

import com.smartcourier.auth.dto.AuthRequest;
import com.smartcourier.auth.dto.AuthResponse;
import com.smartcourier.auth.entity.User;
import com.smartcourier.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.smartcourier.auth.security.JwtUtil;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN ONLY ACCESS
    @GetMapping("/users")
    public List<User> getUsers(@RequestHeader("Authorization") String header) {

        String token = header.substring(7);
        String role = jwtUtil.extractRole(token);

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        return service.getAllUsers();
    }

    // SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.signup(request));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(new AuthResponse(service.login(request)));
    }

    // GET USER BY ID
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // DELETE USER
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User deleted successfully";
    }

    // UPDATE USER
    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id) {
        return "User updated successfully";
    }

    // TOKEN VALIDATION
    @GetMapping("/validate")
    public String validate() {
        return "Token is valid";
    }

    // ROLE CHECK
    @GetMapping("/role")
    public String role() {
        return "ROLE_USER";
    }

    // LOGOUT (DUMMY)
    @PostMapping("/logout")
    public String logout() {
        return "Logged out successfully";
    }
}