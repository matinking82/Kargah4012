package DbContext;

import java.sql.Connection;
import java.util.List;

import DbContext.Interfaces.IExperienceForResumeDbServices;
import Models.ExpeirenceForResume;

public class ExperienceForResumeDbServices implements IExperienceForResumeDbServices{
    
    private Connection connection;

    public ExperienceForResumeDbServices(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ExpeirenceForResume> getAllExpeirenceForResumeList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllArticleForResumeList'");
    }

    @Override
    public ExpeirenceForResume getById(int Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public boolean Add(ExpeirenceForResume expeirenceForResume) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Add'");
    }

    @Override
    public boolean Remove(int expeirenceForResumeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Remove(ExpeirenceForResume expeirenceForResume) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Update(ExpeirenceForResume expeirenceForResume) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }

}
