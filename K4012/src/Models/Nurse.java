package Models;
public class Nurse extends Personel {
    private String type;
    private String placeOfWork;
    
    public Nurse(String name, String phoneNumber, String emai, int age, int id, long salary, String shift,
            boolean isAvalable, String username, String password, String type, String placeOfWork , Gender gender , int off) {
        super(name, phoneNumber, emai, age, id, salary, shift, isAvalable, username, password , gender , off);
        this.type = type;
        this.placeOfWork = placeOfWork;
    }
    public Nurse() {
        super();
    }
    
    public String getPlaceOfWork() {
        return placeOfWork;
    }
    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
