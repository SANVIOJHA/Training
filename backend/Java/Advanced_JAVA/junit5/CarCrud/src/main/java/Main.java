package com.example;

import com.example.entity.Car;
import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("car-unit");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        // ----------------- INSERT -----------------
        tx.begin();
        Car car = new Car("BMW", 5000000);
        em.persist(car);
        tx.commit();

        // ----------------- FETCH -----------------
        Car fetchedCar = em.find(Car.class, 1);
        System.out.println("Fetched: " +
                fetchedCar.getBrandName() + " " +
                fetchedCar.getPrice());

        // ----------------- UPDATE -----------------
        tx.begin();
        fetchedCar.setPrice(5500000);
        em.merge(fetchedCar);
        tx.commit();

        // ----------------- DELETE -----------------
        tx.begin();
        Car deleteCar = em.find(Car.class, 1);
        em.remove(deleteCar);
        tx.commit();

        em.close();
        emf.close();
    }
}