package DbContext;

import DbContext.Interfaces.IAdminDbServices;
import DbContext.Interfaces.IDataBaseContext;
import DbContext.Interfaces.IDoctorDbServices;
import DbContext.Interfaces.INoteDbServices;
import DbContext.Interfaces.INurseDbServices;
import DbContext.Interfaces.IPatientDbServices;
import DbContext.Interfaces.IVisitDbServices;

public class DataBaseContext implements IDataBaseContext{

    private IAdminDbServices admins;
    private IDoctorDbServices doctors;
    private INoteDbServices notes;
    private INurseDbServices nurses;
    private IPatientDbServices patients;
    private IVisitDbServices visits;

    @Override
    public IAdminDbServices Admins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Admins'");
    }

    @Override
    public IDoctorDbServices Doctors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Doctors'");
    }

    @Override
    public INoteDbServices Notes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Notes'");
    }

    @Override
    public INurseDbServices Nurses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Nurses'");
    }

    @Override
    public IPatientDbServices Patients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Patients'");
    }

    @Override
    public IVisitDbServices Visits() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Visits'");
    }
    
}
