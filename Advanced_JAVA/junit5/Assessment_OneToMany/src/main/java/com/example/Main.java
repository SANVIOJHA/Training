package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Person person = new Person("Sanvi", "Delhi");

        Aadhaar a1 = new Aadhaar("1234-5678-9012", "2020");
        Aadhaar a2 = new Aadhaar("9876-5432-1098", "2022");

        person.addAadhaar(a1);
        person.addAadhaar(a2);

        session.persist(person);

        tx.commit();
        session.close();

        System.out.println("Data saved successfully!");
    }
}
