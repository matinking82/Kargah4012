public class Doctor extends Personel{
    private String Expertise;
    

    public Doctor() {
        super();
    }


    public Doctor(String name, String phoneNumber, String emai, int age, int id, long salary, String shift,
            boolean isAvalable, String username, String password, String expertise , Gender gender) {
        super(name, phoneNumber, emai, age, id, salary, shift, isAvalable, username, password ,gender);
        Expertise = expertise;
    }
    

    public String getExpertise() {
        return Expertise;
    }

    public void setExpertise(String expertise) {
        Expertise = expertise;
    }

    
}
