package Models;
public class Personel extends Person{
    private long salary;
    private String shift; 
    private boolean isAvalable;
    private String username;
    private String password;
    private int off;


    public Personel(String name, String phoneNumber, String emai, int age, long salary, String shift,
            boolean isAvalable, String username, String password ,String gender , int off) {
        super(name, phoneNumber, emai, age ,  gender);
        this.salary = salary;
        this.shift = shift;
        this.isAvalable = isAvalable;
        this.username = username;
        this.password = password;
        this.off=off;
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
    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
    }
    
}
