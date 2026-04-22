package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "aadhaar")
public class Aadhaar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String aadhaarNumber;

    private String issueDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Aadhaar() {}

    public Aadhaar(String aadhaarNumber, String issueDate) {
        this.aadhaarNumber = aadhaarNumber;
        this.issueDate = issueDate;
    }

    public int getId() { return id; }
    public String getAadhaarNumber() { return aadhaarNumber; }
    public String getIssueDate() { return issueDate; }
    public Person getPerson() { return person; }

    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
    public void setPerson(Person person) { this.person = person; }
}
