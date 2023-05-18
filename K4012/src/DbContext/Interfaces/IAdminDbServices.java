package DbContext.Interfaces;

import java.util.List;

import Models.Admin;

public interface IAdminDbServices {
    public List<Admin> getAllAdminsList();
    public Admin getById(int Id);
    public boolean Add(Admin admin);
    public boolean Remove(int adminId);
    public boolean Remove(Admin admin);
    public boolean Update(Admin admin);
}
