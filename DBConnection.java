package com.mycompany.parceltracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // 1. Updated URL prefix to 'jdbc:mariadb'
    private static final String URL = "jdbc:mariadb://localhost:3306/parcelTrackingDB?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = ""; // Add your MariaDB password here if you have one

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 2. Updated to the official MariaDB JDBC Driver class
            Class.forName("org.mariadb.jdbc.Driver");
            
            // Establish the connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Database Connection Error: MariaDB JDBC Driver not found. Ensure the mariadb-java-client JAR is in your project libraries.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database Connection Error: Failed to connect to MariaDB 'parcelTrackingDB'. Check if your MariaDB server is running.");
            e.printStackTrace();
        }
        return connection;
    }
}