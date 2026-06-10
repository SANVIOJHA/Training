package com.smartcourier.delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class FixFlyway {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartcourier_delivery_db?useSSL=false", "root", "root");
            Statement stmt = conn.createStatement();
            
            // Delete the failed migration tracking row
            stmt.executeUpdate("DELETE FROM flyway_schema_history WHERE version = '2'");
            System.out.println("Deleted flyway schema history version 2");
            
            // Drop the half-created hubs table
            stmt.executeUpdate("DROP TABLE IF EXISTS hubs");
            System.out.println("Dropped table hubs");
            
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
