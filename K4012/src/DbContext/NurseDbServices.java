package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.INurseDbServices;
import Models.Nurse;

public class NurseDbServices implements INurseDbServices {
    private Connection connection;

    public NurseDbServices(Connection connection) {
        this.connection = connection;
    }
    private int getLastNurseId() {
        int lastNurseId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Nurse;";
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
    public List<Nurse> getAllNursesList() {
        List<Nurse> nurses = new ArrayList<>();
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Nurse";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Loop through the result set and add each Nurse object to the list
            while (rs.next()) {
                Nurse nurse = new Nurse();
                nurse.setId(rs.getInt("id"));
                nurse.setName(rs.getString("name"));
                nurse.setGender(rs.getString("gender"));
                nurse.setPhoneNumber(rs.getString("phoneNumber"));
                nurse.setEmail(rs.getString("email"));
                nurse.setAge(rs.getInt("age"));
                nurse.setSalary(rs.getLong("salary"));
                nurse.setShift(rs.getString("shift"));
                nurse.setAvalable(rs.getBoolean("isAvailable"));
                nurse.setUsername(rs.getString("username"));
                nurse.setPassword(rs.getString("password"));
                nurse.setType(rs.getString("type"));
                nurse.setPlaceOfWork(rs.getString("placeOfWork"));
                nurses.add(nurse);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all nurses: " + e.getMessage());
        }
        return nurses;
    }

    @Override
    public Nurse getById(int Id) {
        Nurse nurse = null;
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Nurse WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row and create a Nurse object
            if (rs.next()) {
                nurse = new Nurse();
                nurse.setId(rs.getInt("id"));
                nurse.setName(rs.getString("name"));
                nurse.setGender(rs.getString("gender"));
                nurse.setPhoneNumber(rs.getString("phoneNumber"));
                nurse.setEmail(rs.getString("email"));
                nurse.setAge(rs.getInt("age"));
                nurse.setSalary(rs.getLong("salary"));
                nurse.setShift(rs.getString("shift"));
                nurse.setAvalable(rs.getBoolean("isAvailable"));
                nurse.setUsername(rs.getString("username"));
                nurse.setPassword(rs.getString("password"));
                nurse.setType(rs.getString("type"));
                nurse.setPlaceOfWork(rs.getString("placeOfWork"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting nurse by ID: " + e.getMessage());
        }
        return nurse;
    }

    @Override
    public boolean Add(Nurse nurse) {
        boolean success = false;
        nurse.setId(getLastNurseId()+1);
        try {
            // Prepare the SQL statement
            String sql = "INSERT INTO Nurse (name, gender, phoneNumber, email, age, salary, shift, isAvailable, username, password, type, placeOfWork,id) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nurse.getName());
            statement.setString(2, nurse.getGender());
            statement.setString(3, nurse.getPhoneNumber());
            statement.setString(4, nurse.getEmail());
            statement.setInt(5, nurse.getAge());
            statement.setLong(6, nurse.getSalary());
            statement.setString(7, nurse.getShift());
            statement.setBoolean(8, nurse.isAvalable());
            statement.setString(9, nurse.getUsername());
            statement.setString(10, nurse.getPassword());
            statement.setString(11, nurse.getType());
            statement.setString(12, nurse.getPlaceOfWork());
            statement.setInt(13, nurse.getId());

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding nurse: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int nurseId) {
        boolean success = false;
        try {
            // Prepare the SQL statement
            String sql = "DELETE FROM Nurse WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nurseId);

            // Execute the SQL statement
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error removing nurse: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(Nurse nurse) {
        boolean success = false;
        try {
            // Prepare the SQL statement
            String sql = "DELETE FROM Nurse WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nurse.getId());

            // Execute the SQL statement
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error removing nurse: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Update(Nurse nurse) {
        boolean success = false;
        try {
            // Prepare the SQL statement
            String sql = "UPDATE Nurse SET name=?, gender=?, phoneNumber=?, email=?, age=?, salary=?, shift=?, isAvailable=?, username=?, password=?, type=?, placeOfWork=? " +
                         "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nurse.getName());
            statement.setString(2, nurse.getGender());
            statement.setString(3, nurse.getPhoneNumber());
            statement.setString(4, nurse.getEmail());
            statement.setInt(5, nurse.getAge());
            statement.setLong(6, nurse.getSalary());
            statement.setString(7, nurse.getShift());
            statement.setBoolean(8, nurse.isAvalable());
            statement.setString(9, nurse.getUsername());
            statement.setString(10, nurse.getPassword());
            statement.setString(11, nurse.getType());
            statement.setString(12, nurse.getPlaceOfWork());
            statement.setInt(13, nurse.getId());

            // Execute the SQL statement
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating nurse: " + e.getMessage());
        }
        return success;
    }
    @Override
    public Nurse IsExist(String username, String password) {
        Nurse nurse = null;
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Nurse WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row and create a Nurse object
            if (rs.next()) {
                nurse = new Nurse();
                nurse.setId(rs.getInt("id"));
                nurse.setName(rs.getString("name"));
                nurse.setGender(rs.getString("gender"));
                nurse.setPhoneNumber(rs.getString("phoneNumber"));
                nurse.setEmail(rs.getString("email"));
                nurse.setAge(rs.getInt("age"));
                nurse.setSalary(rs.getLong("salary"));
                nurse.setShift(rs.getString("shift"));
                nurse.setAvalable(rs.getBoolean("isAvailable"));
                nurse.setUsername(rs.getString("username"));
                nurse.setPassword(rs.getString("password"));
                nurse.setType(rs.getString("type"));
                nurse.setPlaceOfWork(rs.getString("placeOfWork"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting nurse by ID: " + e.getMessage());
        }
        return nurse;
    }
}