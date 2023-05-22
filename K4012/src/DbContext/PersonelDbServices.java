package DbContext;

import java.sql.Connection;
import java.util.List;

import DbContext.Interfaces.IPersonelDbServices;
import Models.Personel;

public class PersonelDbServices implements IPersonelDbServices {

    private Connection connection;

    public PersonelDbServices(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Personel> getAllPersonelsList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPersonelsList'");
    }

    @Override
    public Personel getById(int Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public boolean Add(Personel personel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Add'");
    }

    @Override
    public boolean Remove(int visitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Remove(Personel personel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Update(Personel personel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }

}
