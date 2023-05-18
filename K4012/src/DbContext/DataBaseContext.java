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

            // Admins
            String admin = "CREATE TABLE IF NOT EXISTS Admin (\n"
                    + "password NVARCHAR(255),"
                    + "id INT ,"
                    + "username NVARCHAR(255),"
                    + "name NVARCHAR(255),"
                    + "phoneNumber NVARCHAR(255),"
                    + "email NVARCHAR(255),"
                    + "age INT"
                    + ");";
            ////// Doctorssql_more
            String doctor = "CREATE TABLE IF NOT EXISTS Doctor (" +
                    "name NVARCHAR(255)," +
                    "gender NVARCHAR(255)," +
                    "phoneNumber NVARCHAR(255)," +
                    "email NVARCHAR(255)," +
                    "age INT," +
                    "id INT ," +
                    "salary BIGINT," +
                    "shift NVARCHAR(255)," +
                    "isAvailable BIT," +
                    "username NVARCHAR(255)," +
                    "password NVARCHAR(255)," +
                    "off INT," +
                    "expertise NVARCHAR(255)" +
                    ");";
            String nurse =
                    // Nurses
                    "CREATE TABLE IF NOT EXISTS Nurse (" +
                            "name NVARCHAR(255)," +
                            "gender NVARCHAR(255)," +
                            "phoneNumber NVARCHAR(255)," +
                            "email NVARCHAR(255)," +
                            "age INT," +
                            "id INT ," +
                            "salary BIGINT," +
                            "shift NVARCHAR(255)," +
                            "isAvailable BIT," +
                            "username NVARCHAR(255)," +
                            "password NVARCHAR(255)," +
                            "type NVARCHAR(255)," +
                            "placeOfWork NVARCHAR(255)" +
                            ");";
            // Patients
            String patient = "CREATE TABLE IF NOT EXISTS Patient (" +
                    "name TEXT," +
                    "gender TEXT," +
                    "phoneNumber TEXT," +
                    "email TEXT," +
                    "age INT," +
                    "id INT," +
                    "haveInsured BOOLEAN," +
                    "description TEXT" +
                    ");";

            // Notes
            String note = "CREATE TABLE IF NOT EXISTS Note (" +
                    "id INT," +
                    "VisitId INT," +
                    "note TEXT" +
                    ");";

            String visit =
                    // Visits
                    "CREATE TABLE IF NOT EXISTS Visit (" +
                            "DoctorId INT," +
                            "PatientId INT," +
                            "VisitPrice BIGINT," +
                            "id INT ," +
                            "date NVARCHAR(10)" +
                            ");";
            // Resume
            String resume = "CREATE TABLE Resume (" +
                    "idPersonels INT PRIMARY KEY," +
                    "University NVARCHAR(50)," +
                    "GPA NVARCHAR(10)," +
                    "LevelOfEducation NVARCHAR(50)" +
                    ");";

            // PatientPayment
            String patientPayment = "CREATE TABLE PatientPayment (" +
                    "patientId INT," +
                    "hospitalizationId INT," +
                    "visitId INT," +
                    "isPaid BIT" +
                    ");";
            // PatientHospitalizationRecord
            String patientHospitalizationRecord = "CREATE TABLE PatientHospitalizationRecord (" +
                    "id INT PRIMARY KEY," +
                    "doctorId INT," +
                    "patientId INT," +
                    "hospitalizationPrice BIGINT," +
                    "date NVARCHAR(10)" +
                    ");";

            stmnt.execute(admin);
            stmnt.execute(doctor);
            stmnt.execute(nurse);
            stmnt.execute(note);
            stmnt.execute(patient);
            stmnt.execute(visit);
            stmnt.execute(resume);
            stmnt.execute(patientPayment);
            stmnt.execute(patientHospitalizationRecord);

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
