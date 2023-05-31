package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IVisitDbServices;
import Models.Visit;

public class VisitDbServices implements IVisitDbServices {
    private Connection connection;

    public VisitDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastVisitId() {
        int lastNurseId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Visit;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Get the value of the MaxId column
                lastNurseId = rs.getInt("MaxId");
            }
        } catch (SQLException e) {
            System.out.println("Error getting last admin ID: " + e.getMessage());
        }
        return lastNurseId;
    }

    @Override
    public List<Visit> getAllVisitsList() {
        List<Visit> visits = new ArrayList<Visit>();
        try {
            String sql = "SELECT * FROM Visit";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Visit visit = new Visit();
                visit.setDoctorId(result.getInt("DoctorId"));
                visit.setPatientId(result.getInt("PatientId"));
                visit.setVisitPrice(result.getLong("VisitPrice"));
                visit.setId(result.getInt("id"));
                visit.setDate(result.getString("date"));
                visits.add(visit);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving visits: " + e.getMessage());
        }
        return visits;
    }

    @Override
    public Visit getById(int id) {
        Visit visit = null;
        try {
            String sql = "SELECT * FROM Visit WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                visit = new Visit();
                visit.setDoctorId(result.getInt("DoctorId"));
                visit.setPatientId(result.getInt("PatientId"));
                visit.setVisitPrice(result.getLong("VisitPrice"));
                visit.setId(result.getInt("id"));
                visit.setDate(result.getString("date"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving visit: " + e.getMessage());
        }
        return visit;
    }

    @Override
    public boolean Add(Visit visit) {
        boolean success = false;
        visit.setId(getLastVisitId() + 1);
        try {
            String sql = "INSERT INTO Visit (DoctorId, PatientId, VisitPrice, id, date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, visit.getDoctorId());
            statement.setInt(2, visit.getPatientId());
            statement.setLong(3, visit.getVisitPrice());
            statement.setInt(4, visit.getId());
            statement.setString(5, visit.getDate());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding visit: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int visitId) {
        boolean success = false;
        try {
            String sql = "DELETE FROM Visit WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, visitId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting visit: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(Visit visit) {
        return Remove(visit.getId());
    }

    @Override
    public boolean Update(Visit visit) {
        boolean success = false;
        try {
            String sql = "UPDATE Visit SET DoctorId = ?, PatientId = ?, VisitPrice = ?, date = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, visit.getDoctorId());
            statement.setInt(2, visit.getPatientId());
            statement.setLong(3, visit.getVisitPrice());
            statement.setString(4, visit.getDate());
            statement.setInt(5, visit.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating visit: " + e.getMessage());
        }
        return success;
    }

    @Override
    public List<Visit> GetAllUnacceptedVisits() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetAllUnacceptedVisits'");
    }
}