package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aadhaar_id")
    private Aadhaar aadhaar;

    public Person() {}

    public Person(String name, int age, Aadhaar aadhaar) {
        this.name = name;
        this.age = age;
        this.aadhaar = aadhaar;
    }
}
