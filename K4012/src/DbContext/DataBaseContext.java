package DbContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

            createTables();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTables() {

        try {
            Statement stmnt = connection.createStatement();

            String sql =
                    // Admins
                    "CREATE TABLE IF NOT EXISTS Admin (\n"
                            + "password NVARCHAR(255),"
                            + "id INT," 
                            + "username NVARCHAR(255),"
                            + "name NVARCHAR(255),"
                            + "phoneNumber NVARCHAR(255),"
                            + "email NVARCHAR(255),"
                            + "age INT"
                            + ");"
                            ////// Doctors
                            + "CREATE TABLE IF NOT EXISTS Doctor (" +
                            "name NVARCHAR(255)," +
                            "gender NVARCHAR(255)," +
                            "phoneNumber NVARCHAR(255)," +
                            "email NVARCHAR(255)," +
                            "age INT," +
                            "id INT," +
                            "salary BIGINT," +
                            "shift NVARCHAR(255)," +
                            "isAvailable BIT," +
                            "username NVARCHAR(255)," +
                            "password NVARCHAR(255)," +
                            "expertise NVARCHAR(255)" +
                            ");" +
                            // Notes
                            "CREATE TABLE IF NOT EXISTS Note (" +
                            "id INT," +
                            "VisitId INT," +
                            "note NVARCHAR(MAX)" +
                            ");" +
                            // Nurses
                            "CREATE TABLE IF NOT EXISTS Doctor (" +
                            "name NVARCHAR(255)," +
                            "gender NVARCHAR(255)," +
                            "phoneNumber NVARCHAR(255)," +
                            "email NVARCHAR(255)," +
                            "age INT," +
                            "id INT," +
                            "salary BIGINT," +
                            "shift NVARCHAR(255)," +
                            "isAvailable BIT," +
                            "username NVARCHAR(255)," +
                            "password NVARCHAR(255)," +
                            "type NVARCHAR(255)," +
                            "placeOfWork NVARCHAR(255)" +
                            ");" +
                            // Patients
                            "CREATE TABLE IF NOT EXISTS Patient (" +
                            "name NVARCHAR(255)," +
                            "gender NVARCHAR(255)," +
                            "phoneNumber NVARCHAR(255)," +
                            "email NVARCHAR(255)," +
                            "age INT," +
                            "id INT," +
                            "haveInsured BIT," +
                            "description NVARCHAR(MAX)" +
                            ");" +
                            // Visits
                            "CREATE TABLE IF NOT EXISTS Visit (" +
                            "DoctorId INT," +
                            "PatientId INT," +
                            "VisitPrice BIGINT," +
                            "id INT," +
                            "date NVARCHAR(10)" +
                            ");";
                            //Resume
                            //TODO
                            //PatientPayment
                            //TODO
                            //PatientHospitalizationRecord
                            //TODO
                            /////////////////

            stmnt.execute(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
