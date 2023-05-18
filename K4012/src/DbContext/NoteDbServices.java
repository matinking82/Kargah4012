package DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.Interfaces.INoteDbServices;
import Models.Note;

public class NoteDbServices implements INoteDbServices {
    private Connection connection;

    public NoteDbServices(Connection connection) {
        this.connection = connection;
    }

    private int getLastNoteId() {
        int lastAdminId = -1;
        try {
            // Prepare the SQL statement
            String sql = "SELECT MAX(id) AS MaxId FROM Note;";
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
    public List<Note> getAllNotesList() {
        List<Note> notes = new ArrayList<Note>();
        try {
            String sql = "SELECT * FROM Note";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Note note = new Note();
                note.setId(result.getInt("id"));
                note.setVisitId(result.getInt("VisitId"));
                note.setNote(result.getString("note"));
                notes.add(note);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving notes: " + e.getMessage());
        }
        return notes;
    }

    @Override
    public Note getById(int Id) {
        Note note = null;
        try {
            String sql = "SELECT * FROM Note WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                note = new Note();
                note.setId(result.getInt("id"));
                note.setVisitId(result.getInt("VisitId"));
                note.setNote(result.getString("note"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving note: " + e.getMessage());
        }
        return note;
    }

    @Override
    public boolean Add(Note note) {
        boolean success = false;
        note.setId(getLastNoteId() + 1);
        try {
            String sql = "INSERT INTO Note (id, VisitId, note) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, note.getId());
            statement.setInt(2, note.getVisitId());
            statement.setString(3, note.getNote());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding note: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(int noteId) {
        boolean success = false;
        try {
            String sql = "DELETE FROM Note WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, noteId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting note: " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean Remove(Note note) {
        return Remove(note.getId());
    }

    @Override
    public boolean Update(Note note) {
        boolean success = false;
        try {
            String sql = "UPDATE Note SET VisitId = ?, note = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, note.getVisitId());
            statement.setString(2, note.getNote());
            statement.setInt(3, note.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error updating note: " + e.getMessage());
        }
        return success;
    }
}