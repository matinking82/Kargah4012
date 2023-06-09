package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IDoctorDbServices;
import Models.Doctor;

public class DoctorDbServices implements IDoctorDbServices {
    private Connection connection;

    public DoctorDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastDoctorId() {
        int lastAdminId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Doctor;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Get the value of the MaxId column
                lastAdminId = rs.getInt("MaxId");
            }
        } catch (SQLException e) {
            System.out.println("Error getting last admin ID: " + e.getMessage());
        }
        return lastAdminId;
    }

    @Override
    public List<Doctor> getAllDoctorsList() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Doctor";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Loop through the result set and add each Doctor object to the list
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setPhoneNumber(rs.getString("phoneNumber"));
                doctor.setEmail(rs.getString("email"));
                doctor.setAge(rs.getInt("age"));
                doctor.setOff(rs.getInt("off"));
                doctor.setSalary(rs.getLong("salary"));
                doctor.setShift(rs.getString("shift"));
                doctor.setAvalable(rs.getBoolean("isAvailable"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setExpertise(rs.getString("expertise"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all doctors: " + e.getMessage());
        }
        return doctors;
    }

    @Override
    public Doctor getById(int Id) {
        Doctor doctor = null;
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Doctor WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Create a new Doctor object and populate it with the data from the row
                doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setPhoneNumber(rs.getString("phoneNumber"));
                doctor.setEmail(rs.getString("email"));
                doctor.setAge(rs.getInt("age"));
                doctor.setOff(rs.getInt("off"));
                doctor.setSalary(rs.getLong("salary"));
                doctor.setShift(rs.getString("shift"));
                doctor.setAvalable(rs.getBoolean("isAvailable"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setExpertise(rs.getString("expertise"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting doctor by ID: " + e.getMessage());
        }
        return doctor;
    }

    @Override
    public boolean Add(Doctor doctor) {
        boolean success = false;
        doctor.setId(getLastDoctorId()+1);
        try {
            // Prepare the SQL statement
            String sql = "INSERT INTO Doctor (name, gender, phoneNumber, email, age, salary, shift, isAvailable, username, password, expertise,off,id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getGender());
            statement.setString(3, doctor.getPhoneNumber());
            statement.setString(4, doctor.getEmail());
            statement.setInt(5, doctor.getAge());
            statement.setLong(6, doctor.getSalary());
            statement.setString(7, doctor.getShift());
            statement.setBoolean(8, doctor.isAvalable());
            statement.setString(9, doctor.getUsername());
            statement.setString(10, doctor.getPassword());
            statement.setString(11, doctor.getExpertise());
            statement.setInt(12, doctor.getOff());
            statement.setInt(13, doctor.getId());

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int doctorId) {
        boolean success = false;
        try {
            // Prepare the SQL statement
            String sql = "DELETE FROM Doctor WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, doctorId);

            // Execute the SQL statement
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(Doctor doctor) {
        return Remove(doctor.getId());
    }

    @Override
    public boolean Update(Doctor doctor) {
        boolean success = false;
        try {
            // Prepare the SQL statement
            String sql = "UPDATE Doctor SET name = ?, gender = ?, phoneNumber = ?, email = ?, age = ?, salary = ?, shift = ?, isAvailable = ?, username = ?, password = ?, expertise = ?, off = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getGender());
            statement.setString(3, doctor.getPhoneNumber());
            statement.setString(4, doctor.getEmail());
            statement.setInt(5, doctor.getAge());
            statement.setLong(6, doctor.getSalary());
            statement.setString(7, doctor.getShift());
            statement.setBoolean(8, doctor.isAvalable());
            statement.setString(9, doctor.getUsername());
            statement.setString(10, doctor.getPassword());
            statement.setString(11, doctor.getExpertise());
            statement.setInt(12, doctor.getOff());
            statement.setInt(13, doctor.getId());

            // Execute the SQL statement
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        }
        return success;
    }

    @Override
    public List<Doctor> getAllUnAcceptedDoctorsList() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Doctor WHERE isAvailable = 1;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Loop through the result set and add each Doctor object to the list
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setPhoneNumber(rs.getString("phoneNumber"));
                doctor.setEmail(rs.getString("email"));
                doctor.setAge(rs.getInt("age"));
                doctor.setOff(rs.getInt("off"));
                doctor.setSalary(rs.getLong("salary"));
                doctor.setShift(rs.getString("shift"));
                doctor.setAvalable(rs.getBoolean("isAvailable"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setExpertise(rs.getString("expertise"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all doctors: " + e.getMessage());
        }
        return doctors;
    }

    @Override
    public List<Doctor> getAllAcceptedDoctorsList() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Doctor WHERE isAvailable = 0;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Loop through the result set and add each Doctor object to the list
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setPhoneNumber(rs.getString("phoneNumber"));
                doctor.setEmail(rs.getString("email"));
                doctor.setAge(rs.getInt("age"));
                doctor.setOff(rs.getInt("off"));
                doctor.setSalary(rs.getLong("salary"));
                doctor.setShift(rs.getString("shift"));
                doctor.setAvalable(rs.getBoolean("isAvailable"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setExpertise(rs.getString("expertise"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all doctors: " + e.getMessage());
        }
        return doctors;
    }

    @Override
    public Doctor IsExist(String username, String password) {
        Doctor doctor = null;
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Doctor WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Create a new Doctor object and populate it with the data from the row
                doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setGender(rs.getString("gender"));
                doctor.setPhoneNumber(rs.getString("phoneNumber"));
                doctor.setEmail(rs.getString("email"));
                doctor.setAge(rs.getInt("age"));
                doctor.setOff(rs.getInt("off"));
                doctor.setSalary(rs.getLong("salary"));
                doctor.setShift(rs.getString("shift"));
                doctor.setAvalable(rs.getBoolean("isAvailable"));
                doctor.setUsername(rs.getString("username"));
                doctor.setPassword(rs.getString("password"));
                doctor.setExpertise(rs.getString("expertise"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting doctor by ID: " + e.getMessage());
        }
        return doctor;
    }
}