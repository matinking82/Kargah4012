package Models;

import java.util.List;

public class Resume {
    private String University;
    private String GPA;
    private List<String> WorkExperience;
    private String LevelOfEducation;
    private List<String> Articles;

    public Resume(String university, String gPA, List<String> workExperience, String levelOfEducation,
            List<String> articles) {
        University = university;
        GPA = gPA;
        WorkExperience = workExperience;
        LevelOfEducation = levelOfEducation;
        Articles = articles;
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
    
    public List<String> getWorkExperience() {
        return WorkExperience;
    }
    public void setWorkExperience(List<String> workExperience) {
        WorkExperience = workExperience;
    }
    
    public String getLevelOfEducation() {
        return LevelOfEducation;
    }
    public void setLevelOfEducation(String levelOfEducation) {
        LevelOfEducation = levelOfEducation;
    }
  
    public List<String> getArticles() {
        return Articles;
    }
    public void setArticles(List<String> articles) {
        Articles = articles;
    }


    

    
}
