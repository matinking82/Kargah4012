package Models;

public class Resume {
    private int id;
    private int idPersonels;
    private String University;
    private String GPA;
    // private List<String> WorkExperience;
    private String LevelOfEducation;
    // private List<String> Articles;

    public Resume(String university, String gPA, String levelOfEducation, int idPersonels  ) {
        this.idPersonels=idPersonels;
        this.University = university;
        this.GPA = gPA;
        this.LevelOfEducation = levelOfEducation;
        
    }
    public Resume() {
    }

    
    public String getUniversity() {
        return University;
    }
    public void setUniversity(String university) {
        University = university;
    }
    
    public String getGPA() {
        return GPA;
    }
    public void setGPA(String gPA) {
        GPA = gPA;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLevelOfEducation() {
        return LevelOfEducation;
    }
    public void setLevelOfEducation(String levelOfEducation) {
        LevelOfEducation = levelOfEducation;
    }
    public int getIdPersonels() {
        return idPersonels;
    }
    public void setIdPersonels(int idPersonels) {
        this.idPersonels = idPersonels;
    }


    

    
}
