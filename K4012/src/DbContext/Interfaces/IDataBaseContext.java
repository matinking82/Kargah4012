package DbContext.Interfaces;

public interface IDataBaseContext {

    public IAdminDbServices Admins();
    public IDoctorDbServices Doctors();
    public INoteDbServices Notes();
    public INurseDbServices Nurses();
    public IPatientDbServices Patients();
    public IVisitDbServices Visits();
    public IResumeDbServices Resumes();
    public IPatientPaymentDbServices PatientPayments();
    public IPatientHospitalizationRecordDbServices PatientHospitalizationRecords();
    public IArticleForResumeDbServices ArticleForResumeDbServices();
    public IExperienceForResumeDbServices ExperienceForResumeDbServices();
    public INurseHospitalizationRelationDbServices NurseHospitalizationRelationDbServices();
    
}
