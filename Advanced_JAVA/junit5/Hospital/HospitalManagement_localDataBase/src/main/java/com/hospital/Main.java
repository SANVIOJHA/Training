package com.hospital;

import com.hospital.entity.*;
import com.hospital.service.PatientService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hospital-pu");

        EntityManager em = emf.createEntityManager();

        PatientService patientService = new PatientService(em);

        try {

            em.getTransaction().begin();


            // Create Department

            Department dept = new Department();
            dept.setName("Cardiology");


            // Create 5 Doctors

            for (int i = 1; i <= 5; i++) {

                Doctor doctor = new Doctor();
                doctor.setName("Dr. Doctor " + i);
                doctor.setSpecialization("Specialization " + i);

                dept.addDoctor(doctor);


                // Create 1 Patient per Doctor

                Patient patient = new Patient();
                patient.setName("Patient " + i);
                patient.setDob(LocalDate.of(1990 + i, 4, 15));
                patient.setBloodGroup("A+");
                patient.setPhone("90000000" + i);

                // Medical Record
                MedicalRecord record = new MedicalRecord();
                record.setDiagnosis("Diagnosis " + i);
                record.setRecordDate(LocalDate.now());
                record.setNotes("Under observation");

                patient.setMedicalRecord(record);

                // Link Doctor & Patient
                doctor.addPatient(patient);


                // Appointment

                Appointment appointment = new Appointment();
                appointment.setAppointDate(LocalDateTime.now());
                appointment.setStatus("Scheduled");
                appointment.setReason("Consultation " + i);

                // Prescription
                Prescription prescription = new Prescription();
                prescription.setMedicines("Medicine " + i);
                prescription.setDosage("Twice Daily");
                prescription.setIssuedDate(LocalDate.now());

                appointment.setPrescription(prescription);

                doctor.getAppointments().add(appointment);

                // Persist patient
                em.persist(patient);
            }

            // Persist department (cascade saves doctors)
            em.persist(dept);

            em.getTransaction().commit();

            System.out.println(" 5 Different Doctors & Patients Created Successfully!");


            // FETCH ALL PATIENTS

            List<Patient> allPatients = patientService.getAllPatients();
            System.out.println("Total Patients: " + allPatients.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        System.out.println(" Hospital ERP Project Running Successfully!");
    }
}