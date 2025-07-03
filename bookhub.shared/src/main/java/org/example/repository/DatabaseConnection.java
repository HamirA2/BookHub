package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/bookhubdb",
              "root",
              "Homecourt01$"
            );
        } catch (Exception e) {
            System.err.println("Connection Failed: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance ==  null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
