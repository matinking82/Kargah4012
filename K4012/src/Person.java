public class Person {
    private String name;
    private Gender gender;
   

    private String phoneNumber;
    private String email;
    private int age;
    private int id;

    public Person(String name, String phoneNumber, String emai, int age, int id , Gender gender) {
        this.gender=gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = emai;
        this.age = age;
        this.id = id;
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
    
    public String getEmai() {
        return email;
    }
    public void setEmai(String emai) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
}
