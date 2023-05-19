package Models;

public class ArticleForResume {
    private int id;
    private int resumeId;
    private String name;
    private float impactFactor;

    //TODO
    public ArticleForResume(int id, int resumeId, String name , Float impactFactor) {
        this.id = id;
        this.resumeId = resumeId;
        this.name=name;
        this.impactFactor=impactFactor;
    }
    public ArticleForResume() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getResumeId() {
        return resumeId;
    }
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getImpactFactor() {
        return impactFactor;
    }
    public void setImpactFactor(int impactFactor) {
        this.impactFactor = impactFactor;
    }
}
