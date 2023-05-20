package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.INurseHospitalizationRelationDbServices;
import Models.NurseHospitalizarionRelation;

public class NurseHospitalizationRelationDbServices implements INurseHospitalizationRelationDbServices {
    private Connection connection;

    public NurseHospitalizationRelationDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastId() {
        int lastAdminId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM NurseHospitalizationRelation;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Get the value of the MaxId column
                lastAdminId = rs.getInt("MaxId");
            }
        } catch (SQLException e) {
            System.out.println("Error getting last ID: " + e.getMessage());
        }
        return lastAdminId;
    }

    @Override
    public List<NurseHospitalizarionRelation> getAllNurseHospitalizarionRelationsList() {
        List<NurseHospitalizarionRelation> nurseHospitalizarionRelations = new ArrayList<>();
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM NurseHospitalizationRelation";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Loop through the result set and add each NurseHospitalizarionRelation object
            // to the list
            while (rs.next()) {
                NurseHospitalizarionRelation nurseHospitalizarionRelation = new NurseHospitalizarionRelation();
                nurseHospitalizarionRelation.setId(rs.getInt("id"));
                nurseHospitalizarionRelation.setNurseId(rs.getInt("nurseId"));
                nurseHospitalizarionRelation.setHospitalizationId(rs.getInt("hospitalizationId"));
                nurseHospitalizarionRelations.add(nurseHospitalizarionRelation);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all NurseHospitalizarionRelations: " + e.getMessage());
        }
        return nurseHospitalizarionRelations;
    }

    @Override
    public NurseHospitalizarionRelation getById(int Id) {
        NurseHospitalizarionRelation nurseHospitalizarionRelation = null;
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM NurseHospitalizationRelation WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Create a new NurseHospitalizarionRelation object and set its properties
                nurseHospitalizarionRelation = new NurseHospitalizarionRelation();
                nurseHospitalizarionRelation.setId(rs.getInt("id"));
                nurseHospitalizarionRelation.setNurseId(rs.getInt("nurseId"));
                nurseHospitalizarionRelation.setHospitalizationId(rs.getInt("hospitalizationId"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting NurseHospitalizarionRelation with ID " + Id + ": " + e.getMessage());
        }
        return nurseHospitalizarionRelation;
    }

    @Override
    public boolean Add(NurseHospitalizarionRelation nurseHospitalizarionRelation) {
        nurseHospitalizarionRelation.setId(getLastId() + 1);
        try {
            // Prepare the SQL statement
            String sql = "INSERT INTO NurseHospitalizationRelation (nurseId, hospitalizationId, id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nurseHospitalizarionRelation.getNurseId());
            statement.setInt(2, nurseHospitalizarionRelation.getHospitalizationId());
            statement.setInt(3, nurseHospitalizarionRelation.getId());

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            // Check if the insertion was successful
            if (rowsInserted > 0) {
                System.out.println("A new NurseHospitalizarionRelation was inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting NurseHospitalizarionRelation: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Remove(int nurseHospitalizarionRelationId) {
        try {
            // Prepare the SQL statement
            String sql = "DELETE FROM NurseHospitalizationRelation WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nurseHospitalizarionRelationId);

            // Execute the SQL statement
            int rowsDeleted = statement.executeUpdate();

            // Check if the deletion was successful
            if (rowsDeleted > 0) {
                System.out.println("NurseHospitalizarionRelation with ID " + nurseHospitalizarionRelationId
                        + "was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting NurseHospitalizarionRelation with ID " + nurseHospitalizarionRelationId
                    + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Remove(NurseHospitalizarionRelation nurseHospitalizarionRelation) {
        return Remove(nurseHospitalizarionRelation.getId());
    }

    @Override
    public boolean Update(NurseHospitalizarionRelation nurseHospitalizarionRelation) {
        try {
            // Prepare the SQL statement
            String sql = "UPDATE NurseHospitalizationRelation SET nurseId = ?, hospitalizationId = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nurseHospitalizarionRelation.getNurseId());
            statement.setInt(2, nurseHospitalizarionRelation.getHospitalizationId());
            statement.setInt(3, nurseHospitalizarionRelation.getId());

            // Execute the SQL statement
            int rowsUpdated = statement.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                System.out.println("NurseHospitalizarionRelation with ID " + nurseHospitalizarionRelation.getId()
                        + " was updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating NurseHospitalizarionRelation with ID "
                    + nurseHospitalizarionRelation.getId() + ": " + e.getMessage());
        }
        return false;
    }
}