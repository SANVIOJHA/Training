package com.example.entity;

//import com.example.entity.Student;
import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Student student = new Student("Sanvi", 22);
        Student student1 = new Student("San", 2);

        em.persist(student);

        transaction.commit();

        em.close();
        emf.close();
    }
}
