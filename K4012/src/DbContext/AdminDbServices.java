package DbContext;

import java.sql.Connection;

import DbContext.Interfaces.IAdminDbServices;

public class AdminDbServices implements IAdminDbServices{
    private Connection connection;
    public AdminDbServices(Connection connection) {
        this.connection = connection;
    }
}