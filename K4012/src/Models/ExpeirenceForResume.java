package Models;

public class ExpeirenceForResume {
    private int id;
    private int resumeId;
    private String nameOfWorkplace;
    private String startDate;
    private String endDate;
    
    public ExpeirenceForResume(int id, int resumeId, String nameOfWorkplace, String startDate, String endDate) {
        this.id = id;
        this.resumeId = resumeId;
        this.nameOfWorkplace = nameOfWorkplace;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //TODO
    public ExpeirenceForResume() {
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getNameOfWorkplace() {
        return nameOfWorkplace;
    }
    public void setNameOfWorkplace(String nameOfWorkplace) {
        this.nameOfWorkplace = nameOfWorkplace;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
