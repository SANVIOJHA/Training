package com.hospital.service;

import com.hospital.entity.Doctor;
import jakarta.persistence.*;

import java.util.List;

public class DoctorService {

    private EntityManager em;

    public DoctorService(EntityManager em) {
        this.em = em;
    }

    public void saveDoctor(Doctor doctor) {
        em.persist(doctor);
    }

    public Doctor getDoctor(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> getAllDoctors() {
        return em.createQuery("FROM Doctor", Doctor.class)
                .getResultList();
    }

    public void updateDoctor(Doctor doctor) {
        em.merge(doctor);
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = em.find(Doctor.class, id);
        if (doctor != null) {
            em.remove(doctor);
        }
    }
}