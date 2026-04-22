package com.sprintsmartcourier.auth.controller;


import com.sprintsmartcourier.auth.entity.User;
import com.sprintsmartcourier.auth.repository.UserRepository;
import com.sprintsmartcourier.auth.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final UserRepository userRepository;

    @GetMapping("/profile")
    public User getProfile(Authentication authentication) {
        // Authentication contains CustomUserDetails set by JwtAuthFilter
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
