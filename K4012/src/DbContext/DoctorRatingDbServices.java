package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IDoctorRatingDbServices;
import Models.DoctorRating;

public class DoctorRatingDbServices implements IDoctorRatingDbServices {

    private Connection connection;

    public DoctorRatingDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastId() {
        int lastId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM DoctorRating;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Get the value of the MaxId column
                lastId = rs.getInt("MaxId");
            }
        } catch (SQLException e) {
            System.out.println("Error getting last admin ID: " + e.getMessage());
        }
        return lastId;
    }

    @Override
    public List<DoctorRating> getAllDoctorRatingsList() {
        List<DoctorRating> doctorRatings = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DoctorRating");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int doctorId = resultSet.getInt("doctorId");
                int patientId = resultSet.getInt("patientId");
                float rate = resultSet.getFloat("rate");

                DoctorRating doctorRating = new DoctorRating(doctorId, patientId, rate);
                doctorRating.setId(id);
                doctorRatings.add(doctorRating);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorRatings;
    }

    @Override
    public DoctorRating getById(int id) {
        DoctorRating doctorRating = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DoctorRating WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int doctorId = resultSet.getInt("doctorId");
                int patientId = resultSet.getInt("patientId");
                float rate = resultSet.getFloat("rate");

                doctorRating = new DoctorRating(doctorId, patientId, rate);
                doctorRating.setId(id);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorRating;
    }

    @Override
    public boolean Add(DoctorRating doctorRating) {
        boolean success = false;
        doctorRating.setId(getLastId() + 1);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO DoctorRating (doctorId, patientId, rate, id) VALUES (?, ?, ?, ?)");
            statement.setInt(1, doctorRating.getDoctorId());
            statement.setInt(2, doctorRating.getPatientId());
            statement.setFloat(3, doctorRating.getRate());
            statement.setInt(4, doctorRating.getId());

            int rowsInserted = statement.executeUpdate();
            success = rowsInserted > 0;

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean Remove(int doctorRatingId) {
        boolean success = false;

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM DoctorRating WHERE id = ?");
            statement.setInt(1, doctorRatingId);

            int rowsDeleted = statement.executeUpdate();
            success = rowsDeleted > 0;

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean Remove(DoctorRating doctorRating) {
        return Remove(doctorRating.getId());
    }

    @Override
    public boolean Update(DoctorRating doctorRating) {
        boolean success = false;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE DoctorRating SET doctorId = ?, patientId = ?, rate = ? WHERE id = ?");
            statement.setInt(1, doctorRating.getDoctorId());
            statement.setInt(2, doctorRating.getPatientId());
            statement.setFloat(3, doctorRating.getRate());
            statement.setInt(4, doctorRating.getId());

            int rowsUpdated = statement.executeUpdate();
            success = rowsUpdated > 0;

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}