package DbContext;

import java.sql.Connection;

import DbContext.Interfaces.INurseDbServices;

public class NurseDbServices implements INurseDbServices{
    private Connection connection;
    public NurseDbServices(Connection connection) {
        this.connection = connection;
    }
}
