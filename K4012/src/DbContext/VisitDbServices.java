package DbContext;

import java.sql.Connection;

import DbContext.Interfaces.IVisitDbServices;

public class VisitDbServices implements IVisitDbServices {
    private Connection connection;

    public VisitDbServices(Connection connection) {
        this.connection = connection;
    }
}
