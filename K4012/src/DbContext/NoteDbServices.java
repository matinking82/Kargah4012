package DbContext;

import java.sql.Connection;

import DbContext.Interfaces.INoteDbServices;

public class NoteDbServices implements INoteDbServices{
    private Connection connection;
    public NoteDbServices(Connection connection) {
        this.connection = connection;
    }
}
