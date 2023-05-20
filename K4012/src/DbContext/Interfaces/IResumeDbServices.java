package DbContext.Interfaces;

import java.util.List;

import Models.Resume;

public interface IResumeDbServices {
    public List<Resume> getAllResumesList();
    public Resume getById(int Id);
    public boolean Add(Resume resume);
    public boolean Remove(int resumeId);
    public boolean Remove(Resume resume);
    public boolean Update(Resume resume);
    public List<Resume> getResumesForDoctor(int DoctorId);

}
