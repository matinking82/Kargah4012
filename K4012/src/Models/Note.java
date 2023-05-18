package Models;
public class Note {

    private int id;
    private int visitId;
    private String note;
    
    
    public Note(String note,int visitId) {
        this.note = note;
        this.visitId = visitId;
    } 
    
    public Note() {
    }
    
    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
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
