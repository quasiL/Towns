package cz.towns;

import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:towns.db");
    }
}