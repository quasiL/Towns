package cz.towns;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DatabaseConnection {

    private static Logger logger = LogManager.getLogger(DatabaseConnection.class);

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        logger.info("Connection to database");
        return DriverManager.getConnection("jdbc:sqlite:towns.db");
    }
}