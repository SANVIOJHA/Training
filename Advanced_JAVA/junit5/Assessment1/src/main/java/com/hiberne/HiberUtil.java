package com.hiberne;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HiberUtil {

    private static SessionFactory sessionFactory;

    static {
        try {

            Configuration configuration = new Configuration();

            // Load hibernate.cfg.xml
            configuration.configure();

            // Programmatic properties
            Properties properties = new Properties();
            properties.put("hibernate.connection.driver_class", "org.h2.Driver");
            properties.put("hibernate.connection.url", "jdbc:h2:mem:testdb");
            properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            properties.put("hibernate.hbm2ddl.auto", "update");
            properties.put("hibernate.show_sql", "true");

            configuration.setProperties(properties);

            sessionFactory = configuration.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
