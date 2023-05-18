package Models;
public class Doctor extends Personel{
    private String expertise;
    

    public Doctor() {
        super();
    }


    public Doctor(String name, String phoneNumber, String emai, int age, int id, long salary, String shift,
            boolean isAvalable, String username, String password, String expertise , Gender gender, int off) {
        super(name, phoneNumber, emai, age, id, salary, shift, isAvalable, username, password ,gender , off);
        this.expertise = expertise;
    }
    

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    
}
