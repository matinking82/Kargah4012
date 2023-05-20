package Models;

public class DoctorRating {
    private int id;
    private int doctorId;
    private int patientId;
    private float rate;

    public DoctorRating(int doctorId, int patientId, float rate) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.rate = rate;
    }

    public DoctorRating() {
        
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

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
