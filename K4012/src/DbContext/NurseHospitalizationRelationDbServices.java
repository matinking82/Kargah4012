package DbContext;

import java.sql.Connection;
import java.util.List;

import DbContext.Interfaces.INurseHospitalizationRelationDbServices;
import Models.NurseHospitalizarionRelation;

public class NurseHospitalizationRelationDbServices implements INurseHospitalizationRelationDbServices {
    private Connection connection;

    public NurseHospitalizationRelationDbServices(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<NurseHospitalizarionRelation> getAllNurseHospitalizarionRelationsList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNurseHospitalizarionRelationsList'");
    }

    @Override
    public NurseHospitalizarionRelation getById(int Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public boolean Add(NurseHospitalizarionRelation nurseHospitalizarionRelation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Add'");
    }

    @Override
    public boolean Remove(int nurseHospitalizarionRelationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Remove(NurseHospitalizarionRelation nurseHospitalizarionRelation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Update(NurseHospitalizarionRelation nurseHospitalizarionRelation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }
}
