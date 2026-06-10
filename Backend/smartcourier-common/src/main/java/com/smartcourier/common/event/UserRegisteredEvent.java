package com.smartcourier.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Event published when a new user registers.
 * Published by auth-service, consumed by tracking-service (for SMS welcome).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent implements Serializable {

    private String username;
    private String phoneNumber;
    private String role;
    private LocalDateTime registeredAt;
}
