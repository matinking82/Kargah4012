package DbContext.Interfaces;

import java.util.List;

import Models.PatientHospitalizationRecord;

public interface IPatientHospitalizationRecordDbServices {
    public List<PatientHospitalizationRecord> getAllPatientHospitalizationRecordsList();
    public PatientHospitalizationRecord getById(int Id);
    public boolean Add(PatientHospitalizationRecord patientHospitalizationRecord);
    public boolean Remove(int patientHospitalizationRecordId);
    public boolean Remove(PatientHospitalizationRecord patientHospitalizationRecord);
    public boolean Update(PatientHospitalizationRecord patientHospitalizationRecord);
}
