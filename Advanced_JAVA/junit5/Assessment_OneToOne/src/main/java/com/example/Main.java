package com.example;

import com.example.entity.Aadhaar;
import com.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        SessionFactory factory =
                new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        Person person = new Person("Sanvi", 22);
        Aadhaar aadhaar = new Aadhaar("123456789012", "Delhi");

        person.setAadhaar(aadhaar);
        aadhaar.setPerson(person);

        session.persist(person);

        session.getTransaction().commit();

        session.close();
        factory.close();

        System.out.println("One-To-One Mapping Saved Successfully!");
    }
}
