package DbContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import DbContext.Interfaces.IAdminDbServices;
import DbContext.Interfaces.IArticleForResumeDbServices;
import DbContext.Interfaces.IDataBaseContext;
import DbContext.Interfaces.IDoctorDbServices;
import DbContext.Interfaces.IDoctorRatingDbServices;
import DbContext.Interfaces.IExperienceForResumeDbServices;
import DbContext.Interfaces.INoteDbServices;
import DbContext.Interfaces.INurseDbServices;
import DbContext.Interfaces.INurseHospitalizationRelationDbServices;
import DbContext.Interfaces.IPatientDbServices;
import DbContext.Interfaces.IPatientHospitalizationRecordDbServices;
import DbContext.Interfaces.IPatientPaymentDbServices;
import DbContext.Interfaces.IPersonelDbServices;
import DbContext.Interfaces.IResumeDbServices;
import DbContext.Interfaces.IVisitDbServices;

public class DataBaseContext implements IDataBaseContext {

    private IAdminDbServices admins;
    private IDoctorDbServices doctors;
    private INoteDbServices notes;
    private INurseDbServices nurses;
    private IPatientDbServices patients;
    private IVisitDbServices visits;
    private IResumeDbServices resumes;
    private IPatientPaymentDbServices patientPayments;
    private IPatientHospitalizationRecordDbServices patientHospitalizationRecords;
    private IArticleForResumeDbServices articleForResumeDbServices;
    private IExperienceForResumeDbServices experienceForResumeDbServices;
    private INurseHospitalizationRelationDbServices nurseHospitalizationRelationDbServices;
    private IDoctorRatingDbServices doctorRatingDbServices;
    private IPersonelDbServices personelDbServices;

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
            String resume = "CREATE TABLE IF NOT EXISTS Resume (" +
                    "id INT PRIMARY KEY," +
                    "idPersonels INT," +
                    "University NVARCHAR(50)," +
                    "GPA NVARCHAR(10)," +
                    "LevelOfEducation NVARCHAR(50)" +
                    ");";

            // PatientPayment
            String patientPayment = "CREATE TABLE IF NOT EXISTS PatientPayment (" +
                    "id INT PRIMARY KEY," +
                    "patientId INT," +
                    "hospitalizationId INT," +
                    "visitId INT," +
                    "isPaid BIT" +
                    ");";
            // PatientHospitalizationRecord
            String patientHospitalizationRecord = "CREATE TABLE IF NOT EXISTS PatientHospitalizationRecord (" +
                    "id INT PRIMARY KEY," +
                    "doctorId INT," +
                    "patientId INT," +
                    "hospitalizationPrice BIGINT," +
                    "startDate NVARCHAR(10)," +
                    "endDate NVARCHAR(10)" +
                    ");";
            String expeirenceForResume = "CREATE TABLE IF NOT EXISTS ExpeirenceForResume (" +
                    "id INT," +
                    "resumeId INT NOT NULL," +
                    "nameOfWorkplace VARCHAR(255) NOT NULL," +
                    "startDate NVARCHAR(10) NOT NULL," +
                    "endDate NVARCHAR(10)" +
                    ");";
            String articleForResume = "CREATE TABLE IF NOT EXISTS ArticleForResume (" +
                    "id INT," +
                    "resumeId INT NOT NULL," +
                    "name VARCHAR(255) NOT NULL," +
                    "impactFactor FLOAT NOT NULL" +
                    ");";
            String nurseHospitalizationRelation = "CREATE TABLE IF NOT EXISTS NurseHospitalizarionRelation (" +
                    "id INT," +
                    "nurseId INT NOT NULL," +
                    "hospitalizationId INT NOT NULL" +
                    ");";
            String doctorRating = "CREATE TABLE IF NOT EXISTS DoctorRating (" +
                    "id INT PRIMARY KEY," +
                    "doctorId INT NOT NULL," +
                    "patientId INT NOT NULL," +
                    "rate FLOAT NOT NULL" +
                    ");";

            String personel = "CREATE TABLE IF NOT EXISTS Personel (" +
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
                    "password NVARCHAR(255)"+
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
            stmnt.execute(nurseHospitalizationRelation);
            stmnt.execute(articleForResume);
            stmnt.execute(expeirenceForResume);
            stmnt.execute(doctorRating);
            stmnt.execute(personel);

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
        if (doctors == null) {
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

    @Override
    public IResumeDbServices Resumes() {
        if (resumes == null) {
            resumes = new ResumeDbServices(connection);
        }

        return resumes;
    }

    @Override
    public IPatientPaymentDbServices PatientPayments() {
        if (patientPayments == null) {
            patientPayments = new PatientPaymentDbServices(connection);
        }

        return patientPayments;
    }

    @Override
    public IPatientHospitalizationRecordDbServices PatientHospitalizationRecords() {
        if (patientHospitalizationRecords == null) {
            patientHospitalizationRecords = new PatientHospitalizationRecordDbServices(connection);
        }

        return patientHospitalizationRecords;
    }

    @Override
    public IArticleForResumeDbServices ArticleForResumeDbServices() {
        if (articleForResumeDbServices == null) {
            articleForResumeDbServices = new ArticleForResumeDbServices(connection);
        }

        return articleForResumeDbServices;
    }

    @Override
    public IExperienceForResumeDbServices ExperienceForResumeDbServices() {
        if (experienceForResumeDbServices == null) {
            experienceForResumeDbServices = new ExperienceForResumeDbServices(connection);
        }

        return experienceForResumeDbServices;
    }

    @Override
    public INurseHospitalizationRelationDbServices NurseHospitalizationRelationDbServices() {
        if (nurseHospitalizationRelationDbServices == null) {
            nurseHospitalizationRelationDbServices = new NurseHospitalizationRelationDbServices(connection);
        }

        return nurseHospitalizationRelationDbServices;
    }

    @Override
    public IDoctorRatingDbServices DoctorRatingDbServices() {
        if (doctorRatingDbServices == null) {
            doctorRatingDbServices = new DoctorRatingDbServices(connection);
        }
        return doctorRatingDbServices;
    }

    @Override
    public IPersonelDbServices Personels() {
        if (personelDbServices == null) {
            personelDbServices = new PersonelDbServices(connection);
        }
        return personelDbServices;
    }

}