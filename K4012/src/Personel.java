public class Personel extends Person{
    private long salary;
    private String shift; 
    private boolean isAvalable;
    private String username;
    private String password;


    public Personel(String name, String phoneNumber, String emai, int age, int id, long salary, String shift,
            boolean isAvalable, String username, String password ,Gender gender) {
        super(name, phoneNumber, emai, age, id ,  gender);
        this.salary = salary;
        this.shift = shift;
        this.isAvalable = isAvalable;
        this.username = username;
        this.password = password;
    }

    public Personel() {
        super();
    }
   
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isAvalable() {
        return isAvalable;
    }
    public void setAvalable(boolean isAvalable) {
        this.isAvalable = isAvalable;
    }
    
}
