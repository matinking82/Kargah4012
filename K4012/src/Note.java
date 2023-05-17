public class Note {

    private int id;
  
    private String note;

    public Note(int id, String note) {
        this.id = id;
        this.note = note;
    } 

     public Note() {
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
