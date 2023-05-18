package Models;
public class Person {
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    private int age;
    private int id;

    public Person(String name, String phoneNumber, String emai, int age , String gender) {
        this.gender=gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = emai;
        this.age = age;
    } 

    public Person() {

    }
    


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String emai) {
        this.email = emai;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
