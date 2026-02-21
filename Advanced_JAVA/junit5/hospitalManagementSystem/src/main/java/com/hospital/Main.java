package com.hospital;

import com.hospital.entity.*;
import jakarta.persistence.*;

import java.time.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hospital-pu");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department dept = new Department();
        dept.setName("Cardiology");

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Sharma");
        doctor.setSpecialization("Heart Specialist");

        dept.addDoctor(doctor);

        Patient patient = new Patient();
        patient.setName("Patient1");
        patient.setDob(LocalDate.of(2007,5,10));

        MedicalRecord record = new MedicalRecord();
        record.setDiagnosis("Mild Issue");
        record.setRecordDate(LocalDate.now());
        patient.setMedicalRecord(record);

        doctor.addPatient(patient);

        Appointment appointment = new Appointment();
        appointment.setAppointDate(LocalDateTime.now());
        appointment.setStatus("Scheduled");
        appointment.setReason("Chest Pain");

        Prescription prescription = new Prescription();
        prescription.setMedicines("Dolo");
        prescription.setDosage("2 daily");
        prescription.setIssuedDate(LocalDate.now());

        appointment.setPrescription(prescription);
        doctor.getAppointments().add(appointment);

        em.persist(dept);
        em.persist(patient);

        em.getTransaction().commit();
        em.close();
        emf.close();

        System.out.println(" Hospital ERP Project Running Successfully!-------------");
    }
}