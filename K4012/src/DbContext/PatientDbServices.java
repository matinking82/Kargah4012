package DbContext;

import java.sql.Connection;

import DbContext.Interfaces.IPatientDbServices;

public class PatientDbServices implements IPatientDbServices{
    private Connection connection;
    public PatientDbServices(Connection connection) {
        this.connection = connection;
    }
}
