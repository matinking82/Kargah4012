package Models;

public class PatientHospitalizationRecord {
    
    
    private int id;
    private int doctorId;
    private int patientId;
    // private List<Integer> nursesId;
    private long hospitalizationPrice;
    private String date;


    public PatientHospitalizationRecord(int doctorId, int patientId, long hospitalizationPrice,
             String date) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.hospitalizationPrice = hospitalizationPrice;
        this.date = date;
        
    }
    public PatientHospitalizationRecord()
    {
        
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPoctorId() {
        return doctorId;
    }
    public void setPoctorId(int poctorId) {
        this.doctorId = poctorId;
    }
    
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public long getHospitalizationPrice() {
        return hospitalizationPrice;
    }
    public void setHospitalizationPrice(long hospitalizationPrice) {
        this.hospitalizationPrice = hospitalizationPrice;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    
}
