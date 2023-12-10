package com.example.demo1;

import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class sql_connection{

    private static final String URL = "jdbc:mysql://localhost:3306/signify";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "v=ltX5AtW9v30";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
 }
}
}