package com.prac;

import com.prac.entity.User;
import com.prac.util.HibernateUtil;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        session.beginTransaction();

        User user = new User(1, "Sanvi", "sanvi@gmail.com");

        session.persist(user);

        session.getTransaction().commit();

        session.close();

        System.out.println("User saved successfully!");
    }
}
