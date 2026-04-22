package com.hiberNew;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.*;

public class App {

    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // 1 Insert normal data
            Address address1 = new Address("MG Road", "Mumbai", "Maharashtra", "400001");
            session.save(address1);

            // 2️ Read from file and insert
            try (BufferedReader br = new BufferedReader(new FileReader("address.txt"))) {

                String line;
                while ((line = br.readLine()) != null) {

                    String[] data = line.split(",");

                    Address address = new Address(
                            data[0],
                            data[1],
                            data[2],
                            data[3]
                    );

                    session.save(address);
                }
            } catch (Exception e) {
                System.out.println("address.txt not found (optional)");
            }

            // 3️ Store image (OIP.jpeg)
            File file = new File("OIP.jpeg");

            if (file.exists()) {

                try (FileInputStream fis = new FileInputStream(file)) {

                    byte[] imageData = new byte[(int) file.length()];
                    fis.read(imageData);

                    Address imageAddress = new Address(
                            "Park Street",
                            "Kolkata",
                            "West Bengal",
                            "700016",
                            imageData
                    );

                    session.save(imageAddress);
                }
            } else {
                System.out.println("OIP.jpeg not found!");
            }

            tx.commit();
            System.out.println("Data inserted successfully!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        // 4️ Fetch first record
        Address fetched = session.get(Address.class, 1);

        if (fetched != null) {

            System.out.println("\nFetched Data:");
            System.out.println("ID: " + fetched.getId());
            System.out.println("Street: " + fetched.getStreet());
            System.out.println("City: " + fetched.getCity());
            System.out.println("State: " + fetched.getState());
            System.out.println("Pincode: " + fetched.getPincode());

            // 5️ Write to output.txt
            try (FileWriter writer = new FileWriter("output.txt")) {

                writer.write(fetched.toString());
                System.out.println("Data written to output.txt");

            } catch (IOException e) {
                e.printStackTrace();
            }

            // 6️ Retrieve image
            if (fetched.getImage() != null) {

                try (FileOutputStream fos = new FileOutputStream("retrieved.jpeg")) {

                    fos.write(fetched.getImage());
                    System.out.println("Image retrieved as retrieved.jpeg");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        session.close();
        factory.close();

    }
}
