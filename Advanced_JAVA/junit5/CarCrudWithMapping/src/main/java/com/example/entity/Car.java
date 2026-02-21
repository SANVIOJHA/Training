package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column(nullable = false)
    private String brandName;

    @Column(nullable = false)
    private double price;

    // Many cars → one owner
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Car() {}

    public Car(String brandName, double price) {
        this.brandName = brandName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}