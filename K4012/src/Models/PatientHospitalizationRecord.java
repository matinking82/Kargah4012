package Models;

public class PatientHospitalizationRecord {
    
    
    private int id;
    private int doctorId;
    private int patientId;
    // private List<Integer> nursesId;
    private long hospitalizationPrice;
    private String startDate;
    private String endDate;

    public PatientHospitalizationRecord(int id, int doctorId, int patientId, long hospitalizationPrice,
             String endDate , String startDate) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.hospitalizationPrice = hospitalizationPrice;
        this.endDate = endDate;
        this.startDate=startDate;
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
    
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int poctorId) {
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
    
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
}
