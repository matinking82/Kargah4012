package DbContext.Interfaces;

import java.util.List;

import Models.Note;

public interface INoteDbServices {
    public List<Note> getAllNotesList();
    public Note getById(int Id);
    public boolean Add(Note note);
    public boolean Remove(int noteId);
    public boolean Remove(Note note);
    public boolean Update(Note note);
}
