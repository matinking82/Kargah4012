package DbContext.Interfaces;

import java.util.List;

import Models.ExpeirenceForResume;

public interface IExperienceForResumeDbServices {
    public List<ExpeirenceForResume> getAllExpeirenceForResumeList();
    public ExpeirenceForResume getById(int Id);
    public boolean Add(ExpeirenceForResume expeirenceForResume);
    public boolean Remove(int expeirenceForResumeId);
    public boolean Remove(ExpeirenceForResume expeirenceForResume);
    public boolean Update(ExpeirenceForResume expeirenceForResume);
    public List<ExpeirenceForResume> getExperiencesForResume(int resumeId);
}
