package DbContext.Interfaces;

import java.util.List;

import Models.Patient;

public interface IPatientDbServices {
    public List<Patient> getAllPatientsList();
    public Patient getById(int Id);
    public boolean Add(Patient patient);
    public boolean Remove(int patientId);
    public boolean Remove(Patient patient);
    public boolean Update(Patient patient);
    public Patient IsExist(String email);
}
