package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aadhaar")
public class Aadhaar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String aadhaarNumber;
    private String address;

    public Aadhaar() {}

    public Aadhaar(String aadhaarNumber, String address) {
        this.aadhaarNumber = aadhaarNumber;
        this.address = address;
    }
}
