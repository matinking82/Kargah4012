package Models;

public class NurseHospitalizarionRelation {
    private int id;
    private int nurseId;
    private int hospitalizationId;

  
    public NurseHospitalizarionRelation(int nurseId,int hospitalizationId) {
        this.nurseId = nurseId;
        this.hospitalizationId = hospitalizationId;
    }
    public NurseHospitalizarionRelation() {
        
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNurseId() {
        return nurseId;
    }
    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }
    public int getHospitalizationId() {
        return hospitalizationId;
    }
    public void setHospitalizationId(int hospitalizationId) {
        this.hospitalizationId = hospitalizationId;
    }
}
