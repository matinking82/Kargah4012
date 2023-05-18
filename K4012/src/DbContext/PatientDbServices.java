package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IPatientDbServices;
import Models.Patient;

public class PatientDbServices implements IPatientDbServices {
    private Connection connection;

    public PatientDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastPatientId() {
        int lastNurseId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Patient;";
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
    public List<Patient> getAllPatientsList() {
        List<Patient> patients = new ArrayList<Patient>();
        try {
            String sql = "SELECT * FROM Patient";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Patient patient = new Patient();
                patient.setName(result.getString("name"));
                patient.setGender(result.getString("gender"));
                patient.setPhoneNumber(result.getString("phoneNumber"));
                patient.setEmail(result.getString("email"));
                patient.setAge(result.getInt("age"));
                patient.setId(result.getInt("id"));
                patient.setHaveInsured(result.getBoolean("haveInsured"));
                patient.setDescription(result.getString("description"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patients: " + e.getMessage());
        }
        return patients;
    }

    @Override
    public Patient getById(int id) {
        Patient patient = null;
        try {
            String sql = "SELECT * FROM Patient WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                patient = new Patient();
                patient.setName(result.getString("name"));
                patient.setGender(result.getString("gender"));
                patient.setPhoneNumber(result.getString("phoneNumber"));
                patient.setEmail(result.getString("email"));
                patient.setAge(result.getInt("age"));
                patient.setId(result.getInt("id"));
                patient.setHaveInsured(result.getBoolean("haveInsured"));
                patient.setDescription(result.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient: " + e.getMessage());
        }
        return patient;
    }

    @Override
    public boolean Add(Patient patient) {
        boolean success = false;
        patient.setId(getLastPatientId() + 1);
        try {
            String sql = "INSERT INTO Patient (name, gender, phoneNumber, email, age, id, haveInsured, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getGender());
            statement.setString(3, patient.getPhoneNumber());
            statement.setString(4, patient.getEmail());
            statement.setInt(5, patient.getAge());
            statement.setInt(6, patient.getId());
            statement.setBoolean(7, patient.isHaveInsured());
            statement.setString(8, patient.getDescription());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int patientId) {
        boolean success = false;
        try {
            String sql = "DELETE FROM Patient WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(Patient patient) {
        return Remove(patient.getId());
    }

    @Override
    public boolean Update(Patient patient) {
        boolean success = false;
        try {
            String sql = "UPDATE Patient SET name = ?, gender = ?, phoneNumber = ?, email = ?, age = ?, haveInsured = ?, description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getGender());
            statement.setString(3, patient.getPhoneNumber());
            statement.setString(4, patient.getEmail());
            statement.setInt(5, patient.getAge());
            statement.setBoolean(6, patient.isHaveInsured());
            statement.setString(7, patient.getDescription());
            statement.setInt(8, patient.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
        return success;
    }
}