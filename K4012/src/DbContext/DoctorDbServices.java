package DbContext;

import java.sql.Connection;

import DbContext.Interfaces.IDoctorDbServices;

public class DoctorDbServices implements IDoctorDbServices{
    private Connection connection;

    public DoctorDbServices(Connection connection) {
        this.connection = connection;
    }
    
}
