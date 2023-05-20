package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.IArticleForResumeDbServices;
import Models.ArticleForResume;

public class ArticleForResumeDbServices implements IArticleForResumeDbServices {
    private Connection connection;

    public ArticleForResumeDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastId() {
        int lastId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM ArticleForResume;";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet rs = statement.executeQuery();

            // Check if the result set has a row
            if (rs.next()) {
                // Get the value of the MaxId column
                lastId = rs.getInt("MaxId");
            }
        } catch (SQLException e) {
            System.out.println("Error getting last ID: " + e.getMessage());
        }
        return lastId;
    }

    @Override
    public List<ArticleForResume> getAllArticleForResumeList() {
        List<ArticleForResume> articleList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ArticleForResume");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ArticleForResume article = new ArticleForResume();
                article.setId(resultSet.getInt("id"));
                article.setResumeId(resultSet.getInt("resumeId"));
                article.setName(resultSet.getString("name"));
                article.setImpactFactor(resultSet.getFloat("impactFactor"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    @Override
    public ArticleForResume getById(int id) {
        ArticleForResume article = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ArticleForResume WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                article = new ArticleForResume();
                article.setId(resultSet.getInt("id"));
                article.setResumeId(resultSet.getInt("resumeId"));
                article.setName(resultSet.getString("name"));
                article.setImpactFactor(resultSet.getFloat("impactFactor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public boolean Add(ArticleForResume article) {
        article.setId(getLastId() + 1);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO ArticleForResume (resumeId, name, impactFactor,id) VALUES (?, ?, ?, ?)");
            statement.setInt(1, article.getResumeId());
            statement.setString(2, article.getName());
            statement.setFloat(3, article.getImpactFactor());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Remove(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ArticleForResume WHERE id = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Remove(ArticleForResume article) {
        return Remove(article.getId());
    }

    @Override
    public boolean Update(ArticleForResume article) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ArticleForResume SET resumeId = ?, name = ?, impactFactor = ? WHERE id = ?");
            statement.setInt(1, article.getResumeId());
            statement.setString(2, article.getName());
            statement.setFloat(3, article.getImpactFactor());
            statement.setInt(4, article.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}