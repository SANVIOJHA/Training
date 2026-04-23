package com.smartcourier.admin.client;

import com.smartcourier.admin.dto.RoleUpdateDTO;
import com.smartcourier.admin.dto.UserSummaryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/auth/users")
    List<UserSummaryDTO> getUsers(@RequestHeader("Authorization") String authorization);

    @GetMapping("/auth/users/count")
    long countUsers();

    @PutMapping("/auth/users/{id}/role")
    UserSummaryDTO updateUserRole(@PathVariable Long id, @RequestBody RoleUpdateDTO request);

    @DeleteMapping("/auth/users/{id}")
    String deleteUser(@PathVariable Long id);
}
