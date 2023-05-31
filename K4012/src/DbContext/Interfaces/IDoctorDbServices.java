package DbContext.Interfaces;

import java.util.List;

import Models.Doctor;

public interface IDoctorDbServices {
    public List<Doctor> getAllDoctorsList();
    public Doctor getById(int Id);
    public boolean Add(Doctor doctor);
    public boolean Remove(int doctorId);
    public boolean Remove(Doctor doctor);
    public boolean Update(Doctor doctor);
    public List<Doctor> getAllUnAcceptedDoctorsList();

}
