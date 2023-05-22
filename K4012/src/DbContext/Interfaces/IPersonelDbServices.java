package DbContext.Interfaces;

import java.util.List;

import Models.Personel;

public interface IPersonelDbServices {
    public List<Personel> getAllPersonelsList();
    public Personel getById(int Id);
    public boolean Add(Personel personel);
    public boolean Remove(int visitId);
    public boolean Remove(Personel personel);
    public boolean Update(Personel personel);
}
