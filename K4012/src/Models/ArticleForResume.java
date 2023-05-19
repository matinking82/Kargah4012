package Models;

public class ArticleForResume {
    private int Id;
    private int ResumeId;

    //TODO
    public ArticleForResume(int id, int resumeId) {
        Id = id;
        ResumeId = resumeId;
    }
    public ArticleForResume() {
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getResumeId() {
        return ResumeId;
    }
    public void setResumeId(int resumeId) {
        ResumeId = resumeId;
    }
}
