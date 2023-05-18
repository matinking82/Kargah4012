package DbContext;

import java.sql.Connection;
import java.util.List;

import DbContext.Interfaces.IAdminDbServices;
import Models.Admin;

public class AdminDbServices implements IAdminDbServices{
    private Connection connection;
    public AdminDbServices(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Admin> getAllAdminsList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAdminsList'");
    }
    @Override
    public Admin getById(int Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
    @Override
    public boolean Add(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Add'");
    }
    @Override
    public boolean Remove(int adminId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }
    @Override
    public boolean Remove(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }
    @Override
    public boolean Update(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }
}