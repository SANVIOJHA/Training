package com.hiberNew;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class AddressOperations {

    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();

        getById(session, 1);
        getByMultipleIds(session, Arrays.asList(1, 2, 3));

        session.close();
        factory.close();
    }

    public static void getById(Session session, int id) {

        Address address = session.get(Address.class, id);

        if (address != null) {
            System.out.println(address);
        } else {
            System.out.println("No record found with ID: " + id);
        }
    }

    public static void getByMultipleIds(Session session, List<Integer> ids) {

        String hql = "FROM Address WHERE id IN (:ids)";

        Query<Address> query = session.createQuery(hql, Address.class);
        query.setParameter("ids", ids);

        List<Address> addresses = query.getResultList();

        if (addresses.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Address address : addresses) {
                System.out.println(address);
            }
        }
    }
}
