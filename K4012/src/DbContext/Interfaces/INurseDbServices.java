package DbContext.Interfaces;

import java.util.List;

import Models.Nurse;

public interface INurseDbServices {
    public List<Nurse> getAllNursesList();
    public Nurse getById(int Id);
    public boolean Add(Nurse nurse);
    public boolean Remove(int nurseId);
    public boolean Remove(Nurse nurse);
    public boolean Update(Nurse nurse);
}
