package cz.towns.repositories;

import cz.towns.DatabaseConnection;
import cz.towns.model.TownPart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TownPartsRepository {

    /**
     * Method will add elements to the town_parts table
     * @param townParts list of TownPart entity
     */
    public void addTownParts(List<TownPart> townParts) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();

            for (TownPart townPart : townParts) {
                String sql = "INSERT INTO town_parts (code, name, town_code) VALUES (?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(townPart.getCode()));
                pstmt.setString(2, townPart.getName());
                pstmt.setInt(3, Integer.parseInt(townPart.getTownCode()));
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