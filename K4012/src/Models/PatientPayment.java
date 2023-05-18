package Models;

public class PatientPayment {
    
    private int id;
    private int patientId;
    private int hospitalizationId;
    private int visitId;
    private boolean isPaid;

  

    public PatientPayment(int patientId, int hospitalizationId, int visitId) {
        this.patientId = patientId;
        this.hospitalizationId = hospitalizationId;
        this.visitId = visitId;
    }

    public PatientPayment()
    {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
   
    public int getVisitId() {
        return visitId;
    }
    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }
    
    public int getHospitalizationId() {
        return this.hospitalizationId;
    }
    public void setHospitalizationId(int hospitalizationId) {
        this.hospitalizationId = hospitalizationId;
    }
    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    
}
