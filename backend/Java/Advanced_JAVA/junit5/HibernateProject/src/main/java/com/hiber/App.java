package com.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {

        Configuration config = new Configuration().configure();
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();


        Student s1 = new Student("Sanvi", 22);
        session.save(s1);
        session.persist(s1);

        tx.commit();

        System.out.println("Data Inserted Successfully!");

        session.close();
        factory.close();
    }
}
