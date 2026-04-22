package com.sprint.smartCourier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/// this user class is for both admin and customer


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;///this is where its decided where its customer or admin
    private String email;
    private String password;
    private long phone;
    private boolean active;

    /// /there will be authentication login and role based activity

}
