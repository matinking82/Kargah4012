package DbContext.Interfaces;

import java.util.List;
import Models.NurseHospitalizarionRelation;

public interface INurseHospitalizationRelationDbServices {
    public List<NurseHospitalizarionRelation> getAllNurseHospitalizarionRelationsList();

    public NurseHospitalizarionRelation getById(int Id);

    public boolean Add(NurseHospitalizarionRelation nurseHospitalizarionRelation);

    public boolean Remove(int nurseHospitalizarionRelationId);

    public boolean Remove(NurseHospitalizarionRelation nurseHospitalizarionRelation);

    public boolean Update(NurseHospitalizarionRelation nurseHospitalizarionRelation);
}
