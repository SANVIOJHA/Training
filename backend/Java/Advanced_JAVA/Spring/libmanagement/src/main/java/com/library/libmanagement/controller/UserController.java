package com.library.libmanagement.controller;

import com.library.libmanagement.dto.CustomerDTO;
import com.library.libmanagement.entity.Customer;
import com.library.libmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerDTO dto) {

        try {

            Customer savedCustomer = customerService.registerCustomer(dto);

            if (savedCustomer.getId() != null) {

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("New user details are successfully registered");

            } else {

                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("User registration failed");
            }

        } catch (Exception ex) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred: " + ex.getMessage());
        }
    }
}