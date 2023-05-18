package Models;
import java.util.Date;

public class Visit {

    //private List<Note> notes;
    private int DoctorId;
    private int PatientId;
    private long VisitPrice;
    private int id;
    private String date;//yyyy-MM-dd


    public Visit( int doctorId, int patientId, long visitPrice, int id, Date date) {
        this();
        DoctorId = doctorId;
        PatientId = patientId;
        VisitPrice = visitPrice;
        this.id = id;
        date=new Date();
    }
    public Visit() {
        //notes=new ArrayList<>();
    }

    public int getDoctorId() {
        return DoctorId;
    }
    public void setDoctorId(int doctorId) {
        DoctorId = doctorId;
    }
    public int getPatientId() {
        return PatientId;
    }
    public void setPatientId(int patientId) {
        PatientId = patientId;
    }
    public long getVisitPrice() {
        return VisitPrice;
    }
    public void setVisitPrice(long visitPrice) {
        VisitPrice = visitPrice;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
}
