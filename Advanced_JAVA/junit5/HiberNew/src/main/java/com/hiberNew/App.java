package com.hiberNew;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        // Load configuration
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();

        // -----------------------
        // INSERT DATA
        // -----------------------
        Transaction tx = session.beginTransaction();

        Address address1 = new Address(1, "MG Road", "Mumbai", "Maharashtra", "400001");
        Address address2 = new Address(2, "Brigade Road", "Bangalore", "Karnataka", "560001");

        session.save(address1);
        session.save(address2);

        tx.commit();
        System.out.println("Data inserted successfully!");


        System.out.println("\nFetching Data...");

        Address fetchedAddress = session.get(Address.class, 1);
        System.out.println("ID: " + fetchedAddress.getId());
        System.out.println("Street: " + fetchedAddress.getStreet());
        System.out.println("City: " + fetchedAddress.getCity());
        System.out.println("State: " + fetchedAddress.getState());
        System.out.println("Pincode: " + fetchedAddress.getPincode());

        session.close();
        factory.close();
    }
}
