public class Patient extends Person{
private boolean haveInsured;
private String description;

public Patient(String name, String phoneNumber, String emai, int age, int id, Gender gender, boolean haveInsured,
        String description) {
    super(name, phoneNumber, emai, age, id, gender);
    this.haveInsured = haveInsured;
    this.description = description;
}
public Patient() {
    super();
}

public boolean isHaveInsured() {
    return haveInsured;
}
public void setHaveInsured(boolean haveInsured) {
    this.haveInsured = haveInsured;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}


}
