package Models;

public class ExpeirenceForResume {
    private int Id;
    private int ResumeId;

    //TODO
    
    public ExpeirenceForResume(int id, int resumeId) {
        Id = id;
        ResumeId = resumeId;
    }
    public ExpeirenceForResume() {
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
