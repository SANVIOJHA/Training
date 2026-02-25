package com.hospital.service;

import com.hospital.entity.Patient;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PatientService {

    private EntityManager em;

    public PatientService(EntityManager em) {
        this.em = em;
    }

    // CREATE

    public void savePatient(Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    // READ BY ID

    public Patient findPatientById(Long id) {
        return em.find(Patient.class, id);
    }

    // READ ALL

    public List<Patient> getAllPatients() {
        return em.createQuery("FROM Patient", Patient.class)
                .getResultList();
    }


    // UPDATE

    public void updatePatientPhone(Long id, String newPhone) {
        em.getTransaction().begin();

        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            patient.setPhone(newPhone);
        }

        em.getTransaction().commit();
    }


    // DELETE

    public void deletePatient(Long id) {
        em.getTransaction().begin();

        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            em.remove(patient);
        }

        em.getTransaction().commit();
    }
}