package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IPatientPaymentDbServices;
import Models.PatientPayment;

public class PatientPaymentDbServices implements IPatientPaymentDbServices {
    private Connection connection;

    public PatientPaymentDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastId() {
        int lastNurseId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM PatientPayment;";
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
    public List<PatientPayment> getAllPatientPaymentsList() {
        List<PatientPayment> patientPayments = new ArrayList<PatientPayment>();
        try {
            String sql = "SELECT * FROM PatientPayment";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PatientPayment patientPayment = new PatientPayment();
                patientPayment.setId(result.getInt("id"));
                patientPayment.setPatientId(result.getInt("patientId"));
                patientPayment.setHospitalizationId(result.getInt("hospitalizationId"));
                patientPayment.setVisitId(result.getInt("visitId"));
                patientPayment.setPaid(result.getBoolean("isPaid"));
                patientPayments.add(patientPayment);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient payments: " + e.getMessage());
        }
        return patientPayments;
    }

    @Override
    public PatientPayment getById(int id) {
        PatientPayment patientPayment = null;
        try {
            String sql = "SELECT * FROM PatientPayment WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                patientPayment = new PatientPayment();
                patientPayment.setId(result.getInt("id"));
                patientPayment.setPatientId(result.getInt("patientId"));
                patientPayment.setHospitalizationId(result.getInt("hospitalizationId"));
                patientPayment.setVisitId(result.getInt("visitId"));
                patientPayment.setPaid(result.getBoolean("isPaid"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient payment: " + e.getMessage());
        }
        return patientPayment;
    }

    @Override
    public boolean Add(PatientPayment patientPayment) {
        boolean success = false;
        patientPayment.setId(getLastId() + 1);
        try {
            String sql = "INSERT INTO PatientPayment (id, patientId, hospitalizationId, visitId, isPaid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientPayment.getId());
            statement.setInt(2, patientPayment.getPatientId());
            statement.setInt(3, patientPayment.getHospitalizationId());
            statement.setInt(4, patientPayment.getVisitId());
            statement.setBoolean(5, patientPayment.isPaid());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding patient payment: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int visitId) {
        boolean success = false;
        try {
            String sql = "DELETE FROM PatientPayment WHERE visitId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, visitId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting patient payment: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(PatientPayment patientPayment) {
        return Remove(patientPayment.getVisitId());
    }

    @Override
    public boolean Update(PatientPayment patientPayment) {
        boolean success = false;
        try {
            String sql = "UPDATE PatientPayment SET patientId = ?, hospitalizationId = ?, isPaid = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientPayment.getPatientId());
            statement.setInt(2, patientPayment.getHospitalizationId());
            statement.setBoolean(3, patientPayment.isPaid());
            statement.setInt(4, patientPayment.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating patient payment: " + e.getMessage());
        }
        return success;
    }
}