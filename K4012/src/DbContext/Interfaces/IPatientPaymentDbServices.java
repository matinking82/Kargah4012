package DbContext.Interfaces;

import java.util.List;

import Models.PatientPayment;

public interface IPatientPaymentDbServices {
    public List<PatientPayment> getAllPatientPaymentsList();
    public PatientPayment getById(int Id);
    public boolean Add(PatientPayment patientPayment);
    public boolean Remove(int visitId);
    public boolean Remove(PatientPayment patientPayment);
    public boolean Update(PatientPayment patientPayment);
}
