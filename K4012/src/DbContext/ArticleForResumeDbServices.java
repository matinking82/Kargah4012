package DbContext;

import java.sql.Connection;
import java.util.List;

import DbContext.Interfaces.IArticleForResumeDbServices;
import Models.ArticleForResume;

public class ArticleForResumeDbServices implements IArticleForResumeDbServices{

    private Connection connection;

    public ArticleForResumeDbServices(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ArticleForResume> getAllArticleForResumeList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllArticleForResumeList'");
    }

    @Override
    public ArticleForResume getById(int Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public boolean Add(ArticleForResume articleForResume) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Add'");
    }

    @Override
    public boolean Remove(int articleForResumeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Remove(ArticleForResume articleForResume) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Remove'");
    }

    @Override
    public boolean Update(ArticleForResume articleForResume) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }
    
}
