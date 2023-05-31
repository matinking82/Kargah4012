package DbContext.Interfaces;

import java.util.List;

import Models.Visit;

public interface IVisitDbServices {
    public List<Visit> getAllVisitsList();
    public Visit getById(int Id);
    public boolean Add(Visit visit);
    public boolean Remove(int visitId);
    public boolean Remove(Visit visit);
    public boolean Update(Visit visit);
    public List<Visit> GetAllUnacceptedVisits();
}
