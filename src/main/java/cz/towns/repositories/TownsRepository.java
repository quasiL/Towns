package cz.towns.repositories;

import cz.towns.DatabaseConnection;
import cz.towns.model.Town;

import java.sql.*;
import java.util.List;

public class TownsRepository {

    /**
     * Method will add elements to the towns table
     * @param towns list of Town entity
     */
    public void addTowns(List<Town> towns) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();

            for (Town town : towns) {
                String sql = "INSERT INTO towns (code, name) VALUES (?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(town.getCode()));
                pstmt.setString(2, town.getName());
                pstmt.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}