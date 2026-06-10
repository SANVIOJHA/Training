package com.smartcourier.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcourier.auth.dto.RoleUpdateRequest;
import com.smartcourier.auth.dto.UserUpdateRequest;
import com.smartcourier.auth.dto.request.LoginRequest;
import com.smartcourier.auth.dto.request.SignupRequest;
import com.smartcourier.auth.dto.response.AuthTokenResponse;
import com.smartcourier.auth.dto.response.UserDTO;
import com.smartcourier.auth.exception.GlobalExceptionHandler;
import com.smartcourier.auth.security.JwtUtil;
import com.smartcourier.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private JwtUtil jwtUtil;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new AuthController(authService, jwtUtil))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getUsersShouldReturnAllUsersForAdmin() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");
        when(authService.getAllUsers()).thenReturn(List.of(user(1L, "alice", "CUSTOMER")));

        mockMvc.perform(get("/auth/users").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value("alice"));
    }

    @Test
    void signupShouldReturnSuccessMessage() throws Exception {
        when(authService.signup(any(SignupRequest.class))).thenReturn("User registered successfully");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest("alice", "Secret1!", "1234567890"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully"));
    }

    @Test
    void loginShouldReturnTokens() throws Exception {
        when(authService.login(any(LoginRequest.class))).thenReturn(AuthTokenResponse.builder()
                .accessToken("jwt-token")
                .refreshToken("refresh-token")
                .username("alice")
                .role("CUSTOMER")
                .build());

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest("alice", "Secret1!"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").value("jwt-token"))
                .andExpect(jsonPath("$.data.refreshToken").value("refresh-token"));
    }

    @Test
    void getUserShouldReturnUserById() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");
        when(authService.getUserById(1L)).thenReturn(user(1L, "alice", "CUSTOMER"));

        mockMvc.perform(get("/auth/users/1").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("alice"));
    }

    @Test
    void getUserByUsernameShouldReturnUser() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");
        when(authService.getUserByUsername("alice")).thenReturn(user(1L, "alice", "CUSTOMER"));

        mockMvc.perform(get("/auth/users/username/alice").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.role").value("CUSTOMER"));
    }

    @Test
    void getUsersByRoleShouldReturnMatchingUsers() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");
        when(authService.getUsersByRole("ADMIN")).thenReturn(List.of(user(2L, "admin", "ADMIN")));

        mockMvc.perform(get("/auth/users/role/ADMIN").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value("admin"));
    }

    @Test
    void countUsersShouldReturnCount() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");
        when(authService.countUsers()).thenReturn(3L);

        mockMvc.perform(get("/auth/users/count").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(3));
    }

    @Test
    void deleteUserShouldReturnSuccessMessage() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");

        mockMvc.perform(delete("/auth/users/1").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User deleted successfully"));
    }

    @Test
    void updateUserShouldReturnUpdatedUser() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");
        UserUpdateRequest request = new UserUpdateRequest();
        request.setUsername("alice-updated");
        when(authService.updateUser(eq(1L), any(UserUpdateRequest.class)))
                .thenReturn(user(1L, "alice-updated", "CUSTOMER"));

        mockMvc.perform(put("/auth/users/1")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("alice-updated"));
    }

    @Test
    void updateRoleShouldReturnUpdatedRole() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");
        RoleUpdateRequest request = new RoleUpdateRequest();
        request.setRole("ADMIN");
        when(authService.updateUserRole(eq(1L), any(RoleUpdateRequest.class)))
                .thenReturn(user(1L, "alice", "ADMIN"));

        mockMvc.perform(put("/auth/users/1/role")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.role").value("ADMIN"));
    }

    @Test
    void validateShouldReturnDecodedTokenData() throws Exception {
        when(jwtUtil.extractUsername("token")).thenReturn("alice");
        when(jwtUtil.extractRole("token")).thenReturn("CUSTOMER");

        mockMvc.perform(get("/auth/validate").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("alice"))
                .andExpect(jsonPath("$.data.role").value("CUSTOMER"))
                .andExpect(jsonPath("$.data.valid").value(true));
    }

    @Test
    void roleShouldReturnRoleFromToken() throws Exception {
        when(jwtUtil.extractRole("token")).thenReturn("ADMIN");

        mockMvc.perform(get("/auth/role").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("ADMIN"));
    }

    @Test
    void meShouldReturnCurrentUserDetails() throws Exception {
        when(jwtUtil.extractUsername("token")).thenReturn("alice");
        when(jwtUtil.extractRole("token")).thenReturn("CUSTOMER");

        mockMvc.perform(get("/auth/me").header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("alice"))
                .andExpect(jsonPath("$.data.role").value("CUSTOMER"));
    }

    @Test
    void logoutShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Logged out successfully"));
    }

    private SignupRequest signupRequest(String username, String password, String phoneNumber) {
        SignupRequest request = new SignupRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setPhoneNumber(phoneNumber);
        return request;
    }

    private LoginRequest loginRequest(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        return request;
    }

    private UserDTO user(Long id, String username, String role) {
        return UserDTO.builder()
                .id(id)
                .username(username)
                .phoneNumber("1234567890")
                .role(role)
                .active(true)
                .build();
    }
}
