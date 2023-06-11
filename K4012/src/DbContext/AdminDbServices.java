package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IAdminDbServices;
import Models.Admin;
import Utils.PasswordHasher;

public class AdminDbServices implements IAdminDbServices {
    private Connection connection;

    public AdminDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastAdminId() {
        int lastAdminId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Admin;";
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
    public List<Admin> getAllAdminsList() {
        List<Admin> admins = new ArrayList<>();
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Admin";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Loop through the result set and add each Admin object to the list
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setPassword(rs.getString("password"));
                admin.setUsername(rs.getString("username"));
                admin.setName(rs.getString("name"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
                admin.setEmail(rs.getString("email"));
                admin.setAge(rs.getInt("age"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all admins: " + e.getMessage());
        }
        return admins;
    }

    @Override
    public Admin getById(int adminId) {
        Admin admin = null;
        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM Admin WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, adminId);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Create a new Admin object and set its properties
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setPassword(rs.getString("password"));
                admin.setUsername(rs.getString("username"));
                admin.setName(rs.getString("name"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
                admin.setEmail(rs.getString("email"));
                admin.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting admin with ID " + adminId + ": " + e.getMessage());
        }
        return admin;
    }

    @Override

    public boolean Add(Admin admin) {
        admin.setId(getLastAdminId() + 1);
        try {
            // Prepare the SQL statement
            String sql = "INSERT INTO Admin (password, username, name, phoneNumber, email, age,id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, PasswordHasher.ToSha256(admin.getPassword()));
            statement.setString(2, admin.getUsername());
            statement.setString(3, admin.getName());
            statement.setString(4, admin.getPhoneNumber());
            statement.setString(5, admin.getEmail());
            statement.setInt(6, admin.getAge());
            statement.setInt(7, admin.getId());

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            // Check if the insertion was successful
            if (rowsInserted > 0) {
                System.out.println("A new admin was inserted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting admin: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Remove(int adminId) {
        try {
            // Prepare the SQL statement
            String sql = "DELETE FROM Admin WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, adminId);

            // Execute the SQL statement
            int rowsDeleted = statement.executeUpdate();

            // Check if the deletion was successful
            if (rowsDeleted > 0) {
                System.out.println("Admin with ID " + adminId + " was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting admin with ID " + adminId + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Remove(Admin admin) {
        return Remove(admin.getId());
    }

    @Override
    public boolean Update(Admin admin) {
        try {
            // Prepare the SQL statement
            String sql = "UPDATE Admin SET username = ?, name = ?, phoneNumber = ?, email = ?, age = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getPhoneNumber());
            statement.setString(4, admin.getEmail());
            statement.setInt(5, admin.getAge());
            statement.setInt(6, admin.getId());

            // Execute the SQL statement
            int rowsUpdated = statement.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                System.out.println("Admin with ID " + admin.getId() + " was updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating admin with ID " + admin.getId() + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public Admin IsExist(String username, String password) {
        Admin admin = null;
        try {
            // Prepare the SQL statement
            String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Create a new Admin object and set its properties
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setPassword(rs.getString("password"));
                admin.setUsername(rs.getString("username"));
                admin.setName(rs.getString("name"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
                admin.setEmail(rs.getString("email"));
                admin.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("Error getting admin with username " + username + ": " + e.getMessage());
        }
        return admin;
    }
}