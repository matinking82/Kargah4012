package Models;
public class Note {

    private int id;
    private int VisitId;
    private String note;
    
    
    public Note(int id, String note) {
        this.id = id;
        this.note = note;
    } 
    
    public Note() {
    }
    
    public int getVisitId() {
        return VisitId;
    }

    public void setVisitId(int visitId) {
        VisitId = visitId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    
}
