package com.smartcourier.auth.controller;

import com.smartcourier.auth.dto.request.LoginRequest;
import com.smartcourier.auth.dto.request.SignupRequest;
import com.smartcourier.auth.dto.response.AuthTokenResponse;
import com.smartcourier.auth.dto.response.UserDTO;
import com.smartcourier.auth.dto.RoleUpdateRequest;
import com.smartcourier.auth.dto.UserUpdateRequest;
import com.smartcourier.auth.security.JwtUtil;
import com.smartcourier.auth.service.AuthService;
import com.smartcourier.common.dto.ApiResponse;
import com.smartcourier.common.exception.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@Valid @RequestBody SignupRequest request) {
        String result = service.signup(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthTokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthTokenResponse tokens = service.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", tokens));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<Map<String, String>>> refreshToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new UnauthorizedException("Refresh token is required");
        }
        Map<String, String> tokens = service.refreshToken(refreshToken);
        return ResponseEntity.ok(ApiResponse.success("Token refreshed", tokens));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getUsers(@RequestHeader("Authorization") String header) {
        requireAdmin(header);
        return ResponseEntity.ok(ApiResponse.success("Users retrieved", service.getAllUsers()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable Long id, @RequestHeader("Authorization") String header) {
        requireAdmin(header);
        return ResponseEntity.ok(ApiResponse.success("User retrieved", service.getUserById(id)));
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByUsername(@PathVariable String username, @RequestHeader("Authorization") String header) {
        requireAdmin(header);
        return ResponseEntity.ok(ApiResponse.success("User retrieved", service.getUserByUsername(username)));
    }

    @GetMapping("/users/role/{role}")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getUsersByRole(@PathVariable String role, @RequestHeader("Authorization") String header) {
        requireAdmin(header);
        return ResponseEntity.ok(ApiResponse.success("Users retrieved", service.getUsersByRole(role)));
    }

    @GetMapping("/users/count")
    public ResponseEntity<ApiResponse<Long>> countUsers(@RequestHeader("Authorization") String header) {
        requireAdmin(header);
        return ResponseEntity.ok(ApiResponse.success("User count retrieved", service.countUsers()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String header) {
        requireAdmin(header);
        service.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request, @RequestHeader("Authorization") String header) {
        requireAdmin(header);
        return ResponseEntity.ok(ApiResponse.success("User updated", service.updateUser(id, request)));
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<ApiResponse<UserDTO>> updateRole(@PathVariable Long id, @Valid @RequestBody RoleUpdateRequest request, @RequestHeader("Authorization") String header) {
        requireAdmin(header);
        return ResponseEntity.ok(ApiResponse.success("Role updated", service.updateUserRole(id, request)));
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<Map<String, Object>>> validate(@RequestHeader("Authorization") String header) {
        String token = extractToken(header);
        Map<String, Object> data = Map.of(
                "username", jwtUtil.extractUsername(token),
                "role", jwtUtil.extractRole(token),
                "valid", true
        );
        return ResponseEntity.ok(ApiResponse.success("Token valid", data));
    }

    @GetMapping("/role")
    public ResponseEntity<ApiResponse<String>> role(@RequestHeader("Authorization") String header) {
        String role = jwtUtil.extractRole(extractToken(header));
        return ResponseEntity.ok(ApiResponse.success("Role retrieved", role));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Map<String, String>>> me(@RequestHeader("Authorization") String header) {
        String token = extractToken(header);
        Map<String, String> data = Map.of(
                "username", jwtUtil.extractUsername(token),
                "role", jwtUtil.extractRole(token)
        );
        return ResponseEntity.ok(ApiResponse.success("User info retrieved", data));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        return ResponseEntity.ok(ApiResponse.success("Logged out successfully"));
    }

    private String extractToken(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid Authorization header");
        }
        return header.substring(7);
    }

    /**
     * Defense-in-depth: validates that the caller has the ADMIN role.
     * Even if the API Gateway is bypassed, this check will block unauthorized access.
     */
    private void requireAdmin(String header) {
        String token = extractToken(header);
        String role = jwtUtil.extractRole(token);
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new UnauthorizedException("Access denied: ADMIN role required");
        }
    }
}
