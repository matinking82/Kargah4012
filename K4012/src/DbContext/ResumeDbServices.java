package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IResumeDbServices;
import Models.Resume;

public class ResumeDbServices implements IResumeDbServices {
    private Connection connection;

    public ResumeDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastResumeId() {
        int lastNurseId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Resume;";
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
    public List<Resume> getAllResumesList() {
        List<Resume> resumes = new ArrayList<Resume>();
        try {
            String sql = "SELECT * FROM Resume";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Resume resume = new Resume();
                resume.setId(result.getInt("id"));
                resume.setIdPersonels(result.getInt("idPersonels"));
                resume.setUniversity(result.getString("University"));
                resume.setGPA(result.getString("GPA"));
                resume.setLevelOfEducation(result.getString("LevelOfEducation"));
                resumes.add(resume);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving resumes: " + e.getMessage());
        }
        return resumes;
    }

    @Override
    public Resume getById(int id) {
        Resume resume = null;
        try {
            String sql = "SELECT * FROM Resume WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                resume = new Resume();
                resume.setId(result.getInt("id"));
                resume.setIdPersonels(result.getInt("idPersonels"));
                resume.setUniversity(result.getString("University"));
                resume.setGPA(result.getString("GPA"));
                resume.setLevelOfEducation(result.getString("LevelOfEducation"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving resume: " + e.getMessage());
        }
        return resume;
    }

    @Override
    public boolean Add(Resume resume) {
        boolean success = false;
        resume.setId(getLastResumeId() + 1);
        try {
            String sql = "INSERT INTO Resume (id, idPersonels, University, GPA, LevelOfEducation) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, resume.getId());
            statement.setInt(2, resume.getIdPersonels());
            statement.setString(3, resume.getUniversity());
            statement.setString(4, resume.getGPA());
            statement.setString(5, resume.getLevelOfEducation());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding resume: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int resumeId) {
        boolean success = false;
        try {
            String sql = "DELETE FROM Resume WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, resumeId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting resume: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(Resume resume) {
        return Remove(resume.getId());
    }

    @Override
    public boolean Update(Resume resume) {
        boolean success = false;
        try {
            String sql = "UPDATE Resume SET idPersonels = ?, University = ?, GPA = ?, LevelOfEducation = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, resume.getIdPersonels());
            statement.setString(2, resume.getUniversity());
            statement.setString(3, resume.getGPA());
            statement.setString(4, resume.getLevelOfEducation());
            statement.setInt(5, resume.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating resume: " + e.getMessage());
        }
        return success;
    }

    @Override
public List<Resume> getResumesForDoctor(int doctorId) {
    List<Resume> resumes = new ArrayList<>();
    try {
        // Define the SQL query to retrieve resumes for a given doctor
        String query = "SELECT * FROM Resume WHERE idPersonels = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, doctorId);

        // Execute the SQL query and retrieve the results
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idPersonels = resultSet.getInt("idPersonels");
            String university = resultSet.getString("University");
            String gpa = resultSet.getString("GPA");
            String levelOfEducation = resultSet.getString("LevelOfEducation");
            
            Resume resume = new Resume();
            resume.setId(id);
            resume.setIdPersonels(idPersonels);
            resume.setUniversity(university);
            resume.setGPA(gpa);
            resume.setLevelOfEducation(levelOfEducation);

            resumes.add(resume);
        }

        // Close the statement (the ResultSet is closed automatically when the statement is closed)
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resumes;
}
}