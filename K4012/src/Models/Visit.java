package Models;
import java.util.Date;

public class Visit {

    //private List<Note> notes;
    private int doctorId;
    private int patientId;
    private long visitPrice;
    private int id;
    private String date;//yyyy-MM-dd


    public Visit( int doctorId, int patientId, long visitPrice, int id, Date date) {
        this();
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitPrice = visitPrice;
        this.id = id;
        date=new Date();
    }
    public Visit() {
        //notes=new ArrayList<>();
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
    public long getVisitPrice() {
        return visitPrice;
    }
    public void setVisitPrice(long visitPrice) {
         
        this.visitPrice = visitPrice;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
}
