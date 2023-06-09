package DbContext.Interfaces;

import java.util.List;

import Models.ArticleForResume;

public interface IArticleForResumeDbServices {
    public List<ArticleForResume> getAllArticleForResumeList();
    public ArticleForResume getById(int Id);
    public boolean Add(ArticleForResume articleForResume);
    public boolean Remove(int articleForResumeId);
    public boolean Remove(ArticleForResume articleForResume);
    public boolean Update(ArticleForResume articleForResume);
    public List<ArticleForResume> getAllArticleForResumeList(int resumeId);
}
