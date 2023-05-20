package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IPatientHospitalizationRecordDbServices;
import Models.PatientHospitalizationRecord;

public class PatientHospitalizationRecordDbServices implements IPatientHospitalizationRecordDbServices {
    private Connection connection;

    public PatientHospitalizationRecordDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastId() {
        int lastNurseId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM PatientHospitalizationRecord;";
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
    public List<PatientHospitalizationRecord> getAllPatientHospitalizationRecordsList() {
        List<PatientHospitalizationRecord> patientHospitalizationRecords = new ArrayList<PatientHospitalizationRecord>();
        try {
            String sql = "SELECT * FROM PatientHospitalizationRecord";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PatientHospitalizationRecord patientHospitalizationRecord = new PatientHospitalizationRecord();
                patientHospitalizationRecord.setId(result.getInt("id"));
                patientHospitalizationRecord.setDoctorId(result.getInt("doctorId"));
                patientHospitalizationRecord.setPatientId(result.getInt("patientId"));
                patientHospitalizationRecord.setHospitalizationPrice(result.getLong("hospitalizationPrice"));
                patientHospitalizationRecord.setStartDate(result.getString("startDate"));
                patientHospitalizationRecord.setEndDate(result.getString("endDate"));
                patientHospitalizationRecords.add(patientHospitalizationRecord);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient hospitalization records: " + e.getMessage());
        }
        return patientHospitalizationRecords;
    }

    @Override
    public PatientHospitalizationRecord getById(int id) {
        PatientHospitalizationRecord patientHospitalizationRecord = null;
        try {
            String sql = "SELECT * FROM PatientHospitalizationRecord WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                patientHospitalizationRecord = new PatientHospitalizationRecord();
                patientHospitalizationRecord.setId(result.getInt("id"));
                patientHospitalizationRecord.setDoctorId(result.getInt("doctorId"));
                patientHospitalizationRecord.setPatientId(result.getInt("patientId"));
                patientHospitalizationRecord.setHospitalizationPrice(result.getLong("hospitalizationPrice"));
                patientHospitalizationRecord.setStartDate(result.getString("startDate"));
                patientHospitalizationRecord.setEndDate(result.getString("endDate"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient hospitalization record: " + e.getMessage());
        }
        return patientHospitalizationRecord;
    }

    @Override
    public boolean Add(PatientHospitalizationRecord patientHospitalizationRecord) {
        boolean success = false;
        patientHospitalizationRecord.setId(getLastId() + 1);
        try {
            String sql = "INSERT INTO PatientHospitalizationRecord (id, doctorId, patientId, hospitalizationPrice, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientHospitalizationRecord.getId());
            statement.setInt(2, patientHospitalizationRecord.getDoctorId());
            statement.setInt(3, patientHospitalizationRecord.getPatientId());
            statement.setLong(4, patientHospitalizationRecord.getHospitalizationPrice());
            statement.setString(5, patientHospitalizationRecord.getStartDate());
            statement.setString(6, patientHospitalizationRecord.getEndDate());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding patient hospitalization record: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int patientHospitalizationRecordId) {
        boolean success = false;
        try {
            String sql = "DELETE FROM PatientHospitalizationRecord WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientHospitalizationRecordId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting patient hospitalization record: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(PatientHospitalizationRecord patientHospitalizationRecord) {
        return Remove(patientHospitalizationRecord.getId());
    }

    @Override
    public boolean Update(PatientHospitalizationRecord patientHospitalizationRecord) {
        boolean success = false;
        try {
            String sql = "UPDATE PatientHospitalizationRecord SET doctorId = ?, patientId = ?, hospitalizationPrice = ?, startDate = ?, endDate = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientHospitalizationRecord.getDoctorId());
            statement.setInt(2, patientHospitalizationRecord.getPatientId());
            statement.setLong(3, patientHospitalizationRecord.getHospitalizationPrice());
            statement.setString(4, patientHospitalizationRecord.getStartDate());
            statement.setString(5, patientHospitalizationRecord.getEndDate());
            statement.setInt(6, patientHospitalizationRecord.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating patient hospitalization record: " + e.getMessage());
        }
        return success;
    }
}