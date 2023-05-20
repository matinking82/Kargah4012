package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IExperienceForResumeDbServices;
import Models.ExpeirenceForResume;

public class ExperienceForResumeDbServices implements IExperienceForResumeDbServices {
    private Connection connection;

    public ExperienceForResumeDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastId() {
        int lastId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM ExpeirenceForResume;";
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
    public List<ExpeirenceForResume> getAllExpeirenceForResumeList() {
        List<ExpeirenceForResume> experienceList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ExpeirenceForResume");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ExpeirenceForResume experience = new ExpeirenceForResume();
                experience.setId(resultSet.getInt("id"));
                experience.setResumeId(resultSet.getInt("resumeId"));
                experience.setNameOfWorkplace(resultSet.getString("nameOfWorkplace"));
                experience.setStartDate((resultSet.getString("startDate")));
                experience.setEndDate((resultSet.getString("endDate")));
                experienceList.add(experience);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experienceList;
    }

    @Override
    public ExpeirenceForResume getById(int id) {
        ExpeirenceForResume experience = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ExpeirenceForResume WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                experience = new ExpeirenceForResume();
                experience.setId(resultSet.getInt("id"));
                experience.setResumeId(resultSet.getInt("resumeId"));
                experience.setNameOfWorkplace(resultSet.getString("nameOfWorkplace"));
                experience.setStartDate((resultSet.getString("startDate")));
                experience.setEndDate((resultSet.getString("endDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experience;
    }

    @Override
    public boolean Add(ExpeirenceForResume experience) {
        boolean success = false;
        experience.setId(getLastId() + 1);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO ExpeirenceForResume (resumeId, nameOfWorkplace, startDate, endDate,id) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, experience.getResumeId());
            statement.setString(2, experience.getNameOfWorkplace());
            statement.setString(3, (experience.getStartDate()));
            statement.setString(4, (experience.getEndDate()));
            statement.setInt(5, (experience.getId()));
            int numRowsAffected = statement.executeUpdate();
            success = numRowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean Remove(int id) {
        boolean success = false;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ExpeirenceForResume WHERE id = ?");
            statement.setInt(1, id);
            int numRowsAffected = statement.executeUpdate();
            success = numRowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean Remove(ExpeirenceForResume experience) {
        return Remove(experience.getId());
    }

    @Override
    public boolean Update(ExpeirenceForResume experience) {
        boolean success = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ExpeirenceForResume SET resumeId = ?, nameOfWorkplace = ?, startDate = ?, endDate = ? WHERE id = ?");
            statement.setInt(1, experience.getResumeId());
            statement.setString(2, experience.getNameOfWorkplace());
            statement.setString(3, (experience.getStartDate()));
            statement.setString(4, (experience.getEndDate()));
            statement.setInt(5, experience.getId());
            int numRowsAffected = statement.executeUpdate();
            success = numRowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}