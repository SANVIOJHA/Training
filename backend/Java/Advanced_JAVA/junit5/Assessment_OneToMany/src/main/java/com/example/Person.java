package com.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    @OneToMany(mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Aadhaar> aadhaarList = new ArrayList<>();

    public Person() {}

    public Person(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void addAadhaar(Aadhaar aadhaar) {
        aadhaarList.add(aadhaar);
        aadhaar.setPerson(this);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public List<Aadhaar> getAadhaarList() { return aadhaarList; }

    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
}
