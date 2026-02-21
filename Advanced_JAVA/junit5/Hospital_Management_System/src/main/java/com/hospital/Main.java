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

        //  Create EntityManagerFactory
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hospital-pu");

        // Create EntityManager
        EntityManager em = emf.createEntityManager();

        // Create Service Object
        PatientService patientService = new PatientService(em);

        try {

            // CREATE


            em.getTransaction().begin();

            Department dept = new Department();
            dept.setName("Cardiology");

            Doctor doctor = new Doctor();
            doctor.setName("Dr. Sharma");
            doctor.setSpecialization("Heart Specialist");

            dept.addDoctor(doctor);

            Patient patient = new Patient();
            patient.setName("Rahul Verma");
            patient.setDob(LocalDate.of(2000, 5, 10));
            patient.setBloodGroup("O+");
            patient.setPhone("9876543210");

            MedicalRecord record = new MedicalRecord();
            record.setDiagnosis("Mild Heart Issue");
            record.setRecordDate(LocalDate.now());
            record.setNotes("Regular Checkup Required");

            patient.setMedicalRecord(record);

            doctor.addPatient(patient);

            Appointment appointment = new Appointment();
            appointment.setAppointDate(LocalDateTime.now());
            appointment.setStatus("Scheduled");
            appointment.setReason("Chest Pain");

            Prescription prescription = new Prescription();
            prescription.setMedicines("Dolo 650");
            prescription.setDosage("2 times daily");
            prescription.setIssuedDate(LocalDate.now());

            appointment.setPrescription(prescription);
            doctor.getAppointments().add(appointment);

            em.persist(dept);
            em.persist(patient);

            em.getTransaction().commit();

            System.out.println(" Patient Created Successfully!");
//READ


            Patient fetchedPatient = patientService.findPatientById(patient.getId());
            System.out.println("Fetched Patient: " + fetchedPatient.getName());

         // UPDATE


            patientService.updatePatientPhone(patient.getId(), "9999999999");
            System.out.println("Patient Phone Updated!");



            //  FETCH ALL


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