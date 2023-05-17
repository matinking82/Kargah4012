package DbContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DbContext.Interfaces.IAdminDbServices;
import DbContext.Interfaces.IDataBaseContext;
import DbContext.Interfaces.IDoctorDbServices;
import DbContext.Interfaces.INoteDbServices;
import DbContext.Interfaces.INurseDbServices;
import DbContext.Interfaces.IPatientDbServices;
import DbContext.Interfaces.IVisitDbServices;

public class DataBaseContext implements IDataBaseContext {

    private IAdminDbServices admins;
    private IDoctorDbServices doctors;
    private INoteDbServices notes;
    private INurseDbServices nurses;
    private IPatientDbServices patients;
    private IVisitDbServices visits;

    private Connection connection;

    public DataBaseContext() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            // db parameters
            String url = "jdbc:sqlite:K4012/sqlite/db/office.db";
            // create a connection to the database
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public IAdminDbServices Admins() {

        if (admins == null) {
            admins = new AdminDbServices(connection);
        }

        return admins;
    }

    @Override
    public IDoctorDbServices Doctors() {
        if (doctors == nurses) {
            doctors = new DoctorDbServices(connection);
        }
        return doctors;
    }

    @Override
    public INoteDbServices Notes() {
        if (notes == null) {
            notes = new NoteDbServices(connection);
        }

        return notes;
    }

    @Override
    public INurseDbServices Nurses() {
        if (nurses == null) {
            nurses = new NurseDbServices(connection);
        }

        return nurses;
    }

    @Override
    public IPatientDbServices Patients() {
        if (patients == null) {
            patients = new PatientDbServices(connection);
        }

        return patients;
    }

    @Override
    public IVisitDbServices Visits() {
        if (visits == null) {
            visits = new VisitDbServices(connection);
        }

        return visits;
    }

}
