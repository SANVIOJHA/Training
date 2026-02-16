package com.example;

import com.example.entity.Aadhaar;
import com.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        Aadhaar aadhaar = new Aadhaar("123456789012", "Delhi");
        Person person = new Person("Sanvi", 22, aadhaar);

        session.persist(person);

        session.getTransaction().commit();

        session.close();
        factory.close();

        System.out.println("Data Saved Successfully!");
    }
}
