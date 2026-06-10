package com.smartcourier.tracking.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {
    private String username;
    private String phoneNumber;
    private String role;
    private LocalDateTime registeredAt;
}
