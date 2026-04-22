package com.hiberNew;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "street_name")
    private String street;

    @Column(name = "city_name")
    private String city;

    @Column(name = "state_name")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @Lob
    @Column(name = "image")
    private byte[] image;

    // Default Constructor (Required)
    public Address() {
    }

    // Constructor without image
    public Address(String street, String city, String state, String pincode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    // Constructor with image
    public Address(String street, String city, String state, String pincode, byte[] image) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.image = image;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }

    public byte[] getImage() {
        return image;
    }
}
