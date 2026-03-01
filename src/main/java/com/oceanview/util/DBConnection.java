package com.oceanview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/oceanview_db?useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "1234";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = System.getProperty("test.db.url", DEFAULT_URL);

        if (url.contains("mysql")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } else if (url.contains("h2")) {
            Class.forName("org.h2.Driver");
        }

        return DriverManager.getConnection(url, JDBC_USERNAME, JDBC_PASSWORD);
    }

    public static void closeResources(AutoCloseable connection, AutoCloseable statement, AutoCloseable resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}