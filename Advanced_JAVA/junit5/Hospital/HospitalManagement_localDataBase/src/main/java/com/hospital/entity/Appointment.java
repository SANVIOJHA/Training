package com.hospital.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
/*
Appointment ↔ Prescription (One-to-One | Unidirectional)
One appointment has one prescription
Prescription table linked via foreign key

 */
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointDate;
    private String status;
    private String reason;

    // Uni 1:1
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    public Appointment() {

    }

    public void setAppointDate(LocalDateTime appointDate) {
        this.appointDate = appointDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}