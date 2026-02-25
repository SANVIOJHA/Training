package com.hospital.entity;

import jakarta.persistence.*;
import java.util.*;
/*
Department ↔ Doctor (One-to-Many | Bidirectional)
One department has many doctors
One doctor belongs to one department
Doctor is the owning side (has foreign key)
*/
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String headDoctorName;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
//    FetchType.LAZY is used to improve performance by loading data only when required
    private List<Doctor> doctors = new ArrayList<>();

    public Department() {

    }

    public void addDoctor(Doctor d) {
        doctors.add(d);
        d.setDepartment(this);
    }

    public void removeDoctor(Doctor d) {
        doctors.remove(d);
        d.setDepartment(null);
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}