package com.hospital.service;

import com.hospital.entity.Appointment;
import jakarta.persistence.*;

import java.util.List;

public class AppointmentService {

    private EntityManager em;

    public AppointmentService(EntityManager em) {
        this.em = em;
    }

    public void saveAppointment(Appointment appointment) {
        em.persist(appointment);
    }

    public Appointment getAppointment(Long id) {
        return em.find(Appointment.class, id);
    }

    public List<Appointment> getAllAppointments() {
        return em.createQuery("FROM Appointment", Appointment.class)
                .getResultList();
    }

    public void updateAppointment(Appointment appointment) {
        em.merge(appointment);
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = em.find(Appointment.class, id);
        if (appointment != null) {
            em.remove(appointment);
        }
    }
}