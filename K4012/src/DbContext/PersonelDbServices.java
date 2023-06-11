package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IPersonelDbServices;
import Models.Personel;
import Utils.PasswordHasher;

public class PersonelDbServices implements IPersonelDbServices {

    private Connection connection;

    public PersonelDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastId() {
        int lastNurseId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Personel;";
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
    public List<Personel> getAllPersonelsList() {
        List<Personel> personels = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personel");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Personel personel = new Personel();
                personel.setId(resultSet.getInt("id"));
                personel.setName(resultSet.getString("name"));
                personel.setGender(resultSet.getString("gender"));
                personel.setPhoneNumber(resultSet.getString("phoneNumber"));
                personel.setEmail(resultSet.getString("email"));
                personel.setAge(resultSet.getInt("age"));
                personel.setSalary(resultSet.getLong("salary"));
                personel.setShift(resultSet.getString("shift"));
                personel.setAvalable(resultSet.getBoolean("isAvailable"));
                personel.setUsername(resultSet.getString("username"));
                personel.setPassword(resultSet.getString("password"));
                personels.add(personel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personels;
    }

    @Override
    public Personel getById(int id) {
        Personel personel = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personel WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                personel = new Personel();
                personel.setId(resultSet.getInt("id"));
                personel.setName(resultSet.getString("name"));
                personel.setGender(resultSet.getString("gender"));
                personel.setPhoneNumber(resultSet.getString("phoneNumber"));
                personel.setEmail(resultSet.getString("email"));
                personel.setAge(resultSet.getInt("age"));
                personel.setSalary(resultSet.getLong("salary"));
                personel.setShift(resultSet.getString("shift"));
                personel.setAvalable(resultSet.getBoolean("isAvailable"));
                personel.setUsername(resultSet.getString("username"));
                personel.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personel;
    }

    @Override
    public boolean Add(Personel personel) {
        personel.setId(getLastId() + 1);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Personel (name, gender, phoneNumber, email, age, id, salary, shift, isAvailable, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, personel.getName());
            statement.setString(2, personel.getGender());
            statement.setString(3, personel.getPhoneNumber());
            statement.setString(4, personel.getEmail());
            statement.setInt(5, personel.getAge());
            statement.setInt(6, personel.getId());
            statement.setLong(7, personel.getSalary());
            statement.setString(8, personel.getShift());
            statement.setBoolean(9, personel.isAvalable());
            statement.setString(10, personel.getUsername());
            statement.setString(11, PasswordHasher.ToSha256(personel.getPassword()));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Remove(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Personel WHERE id = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Remove(Personel personel) {
        return Remove(personel.getId());
    }

    @Override
    public boolean Update(Personel personel) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Personel SET name = ?, gender = ?, phoneNumber = ?, email = ?, age = ?, salary = ?, shift = ?, isAvailable = ?, username = ? WHERE id = ?");
            statement.setString(1, personel.getName());
            statement.setString(2, personel.getGender());
            statement.setString(3, personel.getPhoneNumber());
            statement.setString(4, personel.getEmail());
            statement.setInt(5, personel.getAge());
            statement.setLong(6, personel.getSalary());
            statement.setString(7, personel.getShift());
            statement.setBoolean(8, personel.isAvalable());
            statement.setString(9, personel.getUsername());
            statement.setInt(10, personel.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Personel IsExist(String username, String password) {
        Personel personel = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personel WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, PasswordHasher.ToSha256(password));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                personel = new Personel();
                personel.setId(resultSet.getInt("id"));
                personel.setName(resultSet.getString("name"));
                personel.setGender(resultSet.getString("gender"));
                personel.setPhoneNumber(resultSet.getString("phoneNumber"));
                personel.setEmail(resultSet.getString("email"));
                personel.setAge(resultSet.getInt("age"));
                personel.setSalary(resultSet.getLong("salary"));
                personel.setShift(resultSet.getString("shift"));
                personel.setAvalable(resultSet.getBoolean("isAvailable"));
                personel.setUsername(resultSet.getString("username"));
                personel.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personel;
    }

}
