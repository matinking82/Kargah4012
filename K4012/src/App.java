import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DbContext.DataBaseContext;
import DbContext.Interfaces.IDataBaseContext;
import Models.Admin;
import Models.ArticleForResume;
import Models.Doctor;
import Models.ExpeirenceForResume;
import Models.Nurse;
import Models.NurseHospitalizarionRelation;
import Models.Patient;
import Models.PatientHospitalizationRecord;
import Models.PatientPayment;
import Models.Personel;
import Models.Resume;
import Models.Visit;
import Utils.BeutifulMenu;
import Utils.LoginInfo;
import Utils.BeutifulMenu.CreateDoctorCallBack;
import Utils.BeutifulMenu.CreateNurseCallBack;
import Utils.BeutifulMenu.CreatePatientCallBack;
import Utils.BeutifulMenu.CreatePersonelCallBack;
import Utils.BeutifulMenu.CreateVisitRequestCallBack;
import Utils.BeutifulMenu.ListCallback;
import Utils.BeutifulMenu.MenuCallback;
import Utils.BeutifulMenu.SendDoctorResumeCallBack;
import Utils.BeutifulMenu.getEmailCllBack;
import Utils.BeutifulMenu.getUsernamePassCallBack;
import Utils.LoginInfo.loginType;

public class App {
    private static IDataBaseContext context;

    private static void setSeed(){
        List<Admin> admins = context.Admins().getAllAdminsList();
        if (admins.size()==0) {
            Admin admin = new Admin();
            admin.setName("Admin");
            admin.setAge(20);
            admin.setUsername("Admin");
            admin.setPassword("ADMIN");

            context.Admins().Add(admin);
        }
    }
    public static void main(String[] args) throws Exception {
        context = new DataBaseContext();
        BeutifulMenu.context = context;
        setSeed();
        mainMenu();
    }

    public static void mainMenu() {
        LoginInfo.reset();
        List<String> Items = new ArrayList<>();
        Items.add("Admin panel");
        Items.add("Personel panel");
        Items.add("Patient panel");
        Items.add("Send Resume");
        BeutifulMenu.showMenu(Items, "Main menu", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        adminMenu();
                        break;

                    case 2:
                        personelMenu();
                        break;

                    case 3:
                        patientMenu();
                        break;

                    case 4:
                        sendResume();
                        break;

                    default:
                        mainMenu();
                }
            }

        });
    }

    public static void sendResume() {
        BeutifulMenu.getAndSendDoctorResume(new SendDoctorResumeCallBack() {

            @Override
            public void onRequestCreated(Doctor doctor, Resume resume, List<ArticleForResume> articles,
                    List<ExpeirenceForResume> expeirences) {
                context.Doctors().Add(doctor);
                resume.setIdPersonels(doctor.getId());
                context.Resumes().Add(resume);
                for (ExpeirenceForResume expeirenceForResume : expeirences) {
                    expeirenceForResume.setResumeId(resume.getId());
                    context.ExperienceForResumeDbServices().Add(expeirenceForResume);
                }
                for (ArticleForResume articleForResume : articles) {
                    articleForResume.setResumeId(resume.getId());
                    context.ArticleForResumeDbServices().Add(articleForResume);

                }
                mainMenu();

            }

            @Override
            public void onReturn() {
                mainMenu();

            }

        });

    }

    public static void patientMenu() {
        if (!LoginInfo.IsLogged) {
            LoginInfo.type = loginType.Patient;
            login();
            return;
        }
        List<String> Items = new ArrayList<>();
        Items.add("visit Request");
        Items.add("rate Personels");
        Items.add("Payment");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "patient panel", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        visitRequest();
                        break;

                    case 2:
                        ratePersonels();
                        break;

                    case 3:
                        paymentPatient();
                        break;

                    case 4:
                        mainMenu();
                        break;

                    default:
                        personelMenu();
                }

            }

        });

    }

    public static void personelMenu() {
        if (!LoginInfo.IsLogged) {
            login();
            return;
        }
        List<String> Items = new ArrayList<>();
        Items.add("Profile");
        Items.add("Off Request");
        Items.add("Payment");
        Items.add("return menu");

        BeutifulMenu.showMenu(Items, "Personel panel", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        profilePersonels();
                        break;

                    case 2:
                        OffRequest();
                        break;

                    case 3:
                        PaymentPersonel();
                        break;

                    case 4:
                        mainMenu();
                        break;

                    default:
                        personelMenu();

                }

            }

        });

    }

    public static void login() {
        if (LoginInfo.type == loginType.Admin) {
            BeutifulMenu.getUsernamePassFromUser(new getUsernamePassCallBack() {
                @Override
                public void onSubmit(String username, String password) {
                    Admin admin = context.Admins().IsExist(username, password);
                    if (admin != null) {
                        LoginInfo.IsLogged = true;
                        LoginInfo.LoginId = admin.getId();
                    } else {
                        BeutifulMenu.showAlert("Error", "Username or password is incorrect!");
                    }
                    adminMenu();
                }
            });
        } else if (LoginInfo.type == loginType.Patient) {
            List<String> items = new ArrayList<String>();
            items.add("I have an account");
            items.add("create an account");
            BeutifulMenu.showMenu(items, "Login", new MenuCallback() {

                @Override
                public void onMenuSelected(int choice) {
                    if (choice == 1) {
                        BeutifulMenu.getEmailFromUser(new getEmailCllBack() {
                            @Override
                            public void onSubmit(String email) {
                                Patient p = context.Patients().IsExist(email);
                                if (p==null) {
                                    BeutifulMenu.showAlert("Error", "patient with this email doesnt exist");
                                }else{
                                    LoginInfo.IsLogged = true;
                                    LoginInfo.LoginId = p.getId();
                                }
                                patientMenu();
                            }
                        });
                    } else {
                        BeutifulMenu.getPatientFromUser(new CreatePatientCallBack() {
                            @Override
                            public void onPatientCreated(Patient patient) {
                                Patient p = context.Patients().IsExist(patient.getEmail());
                                if (p==null) {
                                    context.Patients().Add(patient);
                                    p = patient;
                                }
                                LoginInfo.IsLogged = true;
                                LoginInfo.LoginId = p.getId();
                                patientMenu();
                            }
                        });
                    }
                }

            });
        } else {
            List<String> items = new ArrayList<String>();
            items.add("Login as doctor");
            items.add("Login as nurse");
            items.add("Login as personel");
            BeutifulMenu.showMenu(items, "Login as", new MenuCallback() {
                @Override
                public void onMenuSelected(int choice) {
                    BeutifulMenu.getUsernamePassFromUser(new getUsernamePassCallBack() {

                        @Override
                        public void onSubmit(String username, String password) {
                            Personel p = null;
                            switch (choice) {
                                case 1: {
                                    p = context.Doctors().IsExist(username, password);
                                    break;
                                }
                                case 2: {
                                    p = context.Nurses().IsExist(username, password);
                                    break;
                                }
                                case 3: {
                                    p = context.Personels().IsExist(username, password);
                                    break;
                                }
                            }

                            if (p != null) {
                                LoginInfo.IsLogged = true;
                                LoginInfo.LoginId = p.getId();
                            } else {
                                BeutifulMenu.showAlert("Error", "Username or password is incorrect!");
                            }
                            personelMenu();
                        }

                    });

                }
            });
        }
    }

    public static void adminMenu() {
        if (!LoginInfo.IsLogged) {
            LoginInfo.type = loginType.Admin;
            login();
            return;
        }
        List<String> Items = new ArrayList<>();
        Items.add("Manage Personel");
        Items.add("Manange Patient");
        Items.add("visits");
        Items.add("Hospitalization");
        Items.add("Profile");
        Items.add("Payment");
        Items.add("Recieved Resumes");
        Items.add("return menu");

        BeutifulMenu.showMenu(Items, "Admin panel", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        managePersonel();
                        break;

                    case 2:
                        manangePatient();
                        break;

                    case 3:
                        visits();
                        break;

                    case 4:
                        hospitalization();
                        break;

                    case 5:
                        ProfileAdmin();
                        break;

                    case 6:
                        Payment();
                        break;

                    case 7:
                        recievedResumes();
                        break;

                    case 8:
                        mainMenu();
                        break;

                    default:
                        adminMenu();
                }

            }
        });
    }

    public static void recievedResumes() {
        List<Doctor> doctors = context.Doctors().getAllUnAcceptedDoctorsList();
        BeutifulMenu.showDoctorsResumeListForAcception(doctors, "List Of Resume", new ListCallback() {

            @Override
            public void onItemSelected(int doctorId) {
                Doctor doctor = context.Doctors().getById(doctorId);

                doctor.setAvalable(true);

                context.Doctors().Update(doctor);
            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                List<Resume> resumes = context.Resumes().getResumesForDoctor(Id);
                for (Resume resume : resumes) {
                    List<ArticleForResume> articles = context.ArticleForResumeDbServices().getAllArticleForResumeList(resume.getId());
                    List<ExpeirenceForResume> expeirences = context.ExperienceForResumeDbServices().getExperiencesForResume(resume.getId());
                    
                    for (ArticleForResume art : articles) {
                        context.ArticleForResumeDbServices().Remove(art);
                    }
                    for (ExpeirenceForResume exp : expeirences) {
                        context.ExperienceForResumeDbServices().Remove(exp);
                    }
                    context.Resumes().Remove(resume);
                }
                context.Doctors().Remove(Id);
            }

            @Override
            public void onReturn() {
                adminMenu();
            }

        });

    }

    public static void Payment() {
        List<String> Items = new ArrayList<>();
        Items.add("paid");
        Items.add("Unpaid");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "Payment", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        paid();
                        break;

                    case 2:
                        Unpaid();
                        break;

                    case 3:
                        adminMenu();
                        break;

                    default:
                        Payment();

                }

            }
        });

    }

    public static void Unpaid() {
    }

    public static void paid() {
    }

    public static void ProfileAdmin() {
        Admin admin = context.Admins().getById(LoginInfo.LoginId);
        List<String> Items = new ArrayList<>();
        Items.add("returne menu");
        BeutifulMenu.showAdminProfile(admin, Items, new MenuCallback() {

            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        adminMenu();
                        break;
                }
            }

        });

    }

    public static void hospitalization() {
        List<String> Items = new ArrayList<>();
        Items.add("Add hospitalizations");
        Items.add("List of hospitalizations");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "hospitalizatio", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        Addhospitalizations();
                        break;

                    case 2:
                        ListOfHospitalizations();
                        break;

                    case 3:
                        adminMenu();
                        break;

                    default:
                        hospitalization();
                }

            }
        });

    }

    public static void ListOfHospitalizations() {
        List<PatientHospitalizationRecord> hospitalizationRecords = context.PatientHospitalizationRecords()
                .getAllPatientHospitalizationRecordsList();
        BeutifulMenu.showHospitalizationList(hospitalizationRecords, "List of Hospitalization", new ListCallback() {

            @Override
            public void onItemSelected(int Id) {

            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                context.PatientHospitalizationRecords().Remove(Id);
                ListOfHospitalizations();
            }

            @Override
            public void onReturn() {

                hospitalization();
            }

        });

    }

    public static void Addhospitalizations() {

        List<Visit> visits = context.Visits().getAllVisitsList();
        BeutifulMenu.showVisitsListForSelection(visits, "List of Visit", new ListCallback() {

            @Override
            public void onItemSelected(int Id) {

                List<Nurse> nurses = context.Nurses().getAllNursesList();
                BeutifulMenu.showNursesListForSelection(nurses, "List Of Nurses", new ListCallback() {

                    @Override
                    public void onItemSelected(int nurseId) {

                        Visit visit = context.Visits().getById(Id);
                        PatientHospitalizationRecord patientHospitalizationRecord = new PatientHospitalizationRecord();
                        patientHospitalizationRecord.setDoctorId(visit.getDoctorId());
                        patientHospitalizationRecord.setPatientId(visit.getPatientId());
                        patientHospitalizationRecord.setStartDate((new Date()).toString());
                        context.PatientHospitalizationRecords().Add(patientHospitalizationRecord);
                        NurseHospitalizarionRelation relation = new NurseHospitalizarionRelation(nurseId,
                                patientHospitalizationRecord.getId());
                        context.NurseHospitalizationRelationDbServices().Add(relation);
                        hospitalization();

                    }

                    @Override
                    public void onItemSelectedForRemove(int Id) {

                    }

                    @Override
                    public void onReturn() {
                        hospitalization();

                    }

                });

            }

            @Override
            public void onItemSelectedForRemove(int Id) {

            }

            @Override
            public void onReturn() {
                hospitalization();
            }

        });
    }

    public static void visits() {
        List<String> Items = new ArrayList<>();
        Items.add("List of visits ");
        Items.add("add visit");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "visit", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        ListOfVisits();
                        break;

                    case 2:
                        addvisit();
                        break;

                    case 3:
                        adminMenu();
                        break;

                    default:
                        visits();

                }

            }
        });
    }

    public static void addvisit() {
        List<Visit> visits = context.Visits().GetAllUnacceptedVisits();
        BeutifulMenu.showVisitsListForAcception(visits, "List Of visit requests", new ListCallback() {

            @Override
            public void onItemSelected(int Id) {
                Visit visit = context.Visits().getById(Id);
                Date date = new Date();
                visit.setDate(date.toString());
                context.Visits().Update(visit);

                PatientPayment payment = new PatientPayment();
                payment.setVisitId(visit.getId());
                payment.setPatientId(visit.getPatientId());
                context.PatientPayments().Add(payment);

                addvisit();

            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                context.Visits().Remove(Id);
                addvisit();

            }

            @Override
            public void onReturn() {
                visits();
            }

        });

    }

    public static void ListOfVisits() {
        List<Visit> visit = context.Visits().getAllVisitsList();
        BeutifulMenu.showVisitsList(visit, "List of Visit", new ListCallback() {

            @Override
            public void onItemSelected(int Id) {

            }

            @Override
            public void onItemSelectedForRemove(int Id) {

                context.Visits().Remove(Id);
                ListOfVisits();

            }

            @Override
            public void onReturn() {
                visits();
            }

        });
    }

    public static void manangePatient() {

        List<String> Items = new ArrayList<>();
        Items.add("List Of Patient");
        Items.add("Add Patient");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "Manage patient", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        ListOfPatient();
                        break;

                    case 2:
                        AddPatient();
                        break;

                    case 3:
                        adminMenu();
                        break;

                    default:
                        manangePatient();

                }

            }
        });

    }

    public static void AddPatient() {
        BeutifulMenu.getPatientFromUser(new CreatePatientCallBack() {

            @Override
            public void onPatientCreated(Patient patient) {
                context.Patients().Add(patient);
                manangePatient();

            }

        });

    }

    public static void ListOfPatient() {
        List<Patient> patients = context.Patients().getAllPatientsList();
        BeutifulMenu.showPatientsList(patients, "List of Patients", new ListCallback() {
            @Override
            public void onItemSelected(int id) {
                Patient patient = context.Patients().getById(id);
                BeutifulMenu.getPatientFromUserForEdit(new CreatePatientCallBack() {

                    @Override
                    public void onPatientCreated(Patient patient) {
                        context.Patients().Update(patient);
                        ListOfPatient();

                    }

                }, patient);

            }

            @Override
            public void onReturn() {
                manangePatient();
            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                context.Patients().Remove(Id);

            }

        });
    }

    public static void managePersonel() {

        List<String> Items = new ArrayList<>();
        Items.add("Doctors");
        Items.add("Nurses");
        Items.add("Add a new Personel");
        Items.add("List Of Personels");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "Manage personel", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        Doctors();
                        break;

                    case 2:
                        Nurses();
                        break;

                    case 3:
                        AddPersonels();
                        break;

                    case 4:
                        ListPersonels();
                        break;

                    case 5:
                        adminMenu();
                        break;

                    default:
                        adminMenu();
                }
            }
        });

    }

    public static void ListPersonels() {
        List<Personel> personels = context.Personels().getAllPersonelsList();
        BeutifulMenu.showPersonelsList(personels, "List of Personels", new ListCallback() {
            @Override
            public void onItemSelected(int id) {
                Personel personel = context.Personels().getById(id);
                BeutifulMenu.getPersonelFromUserForEdit(new CreatePersonelCallBack() {

                    @Override
                    public void onPersonelCreated(Personel personel) {
                        context.Personels().Update(personel);
                        ListPersonels();

                    }

                }, personel);

            }

            @Override
            public void onReturn() {
                managePersonel();
            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                context.Personels().Remove(Id);
                ListPersonels();

            }

        });

    }

    public static void AddPersonels() {
        BeutifulMenu.getPersonelFromUser(new CreatePersonelCallBack() {

            @Override
            public void onPersonelCreated(Personel personel) {

                context.Personels().Add(personel);
                managePersonel();
            }

        });

    }

    public static void Nurses() {
        List<String> Items = new ArrayList<>();
        Items.add("List Of Nurses");
        Items.add("Add Nurses");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "Nurses", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        ListOfNurses();
                        break;

                    case 2:
                        AddNurses();
                        break;

                    case 3:
                        managePersonel();
                        break;

                    default:
                        adminMenu();
                }
            }

        });

    }

    public static void AddNurses() {
        BeutifulMenu.getNurseFromUser(new CreateNurseCallBack() {

            @Override
            public void onNurseCreated(Nurse nurse) {
                context.Nurses().Add(nurse);
                Nurses();
            }

        });

    }

    public static void ListOfNurses() {
        List<Nurse> nurses = context.Nurses().getAllNursesList();
        BeutifulMenu.showNursesList(nurses, "List of Nurses", new ListCallback() {
            @Override
            public void onItemSelected(int id) {
                Nurse nurse = context.Nurses().getById(id);
                BeutifulMenu.getNurseFromUserForEdit(new CreateNurseCallBack() {

                    @Override
                    public void onNurseCreated(Nurse nurse) {
                        context.Nurses().Update(nurse);
                        ListOfNurses();

                    }

                }, nurse);

            }

            @Override
            public void onReturn() {
                Nurses();
            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                context.Nurses().Remove(Id);
                ListOfNurses();
            }

        });

    }

    public static void Doctors() {
        List<String> Items = new ArrayList<>();
        Items.add("List Of Doctors");
        Items.add("Add Doctors");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "Doctors", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        ListOfDoctors();
                        break;

                    case 2: {
                        addDoctors();
                    }
                        break;

                    case 3:
                        managePersonel();
                        break;

                    default:
                        adminMenu();
                }
            }

        });

    }

    public static void addDoctors() {
        BeutifulMenu.getDoctorFromUser(new CreateDoctorCallBack() {
            public void onDoctorCreated(Doctor doctor) {
                doctor.setAvalable(true);
                context.Doctors().Add(doctor);
                Doctors();
            }
        });
    }

    public static void ListOfDoctors() {
        List<Doctor> doctors = context.Doctors().getAllDoctorsList();
        BeutifulMenu.showDoctorsList(doctors, "List of Doctors", new ListCallback() {
            @Override
            public void onItemSelected(int id) {
                Doctor doctor = context.Doctors().getById(id);
                BeutifulMenu.getDoctorFromUserForEdit(new CreateDoctorCallBack() {

                    @Override
                    public void onDoctorCreated(Doctor doctor) {
                        context.Doctors().Update(doctor);
                        ListOfDoctors();
                    }

                }, doctor);

            }

            @Override
            public void onReturn() {
                Doctors();
            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                context.Doctors().Remove(Id);
                ListOfDoctors();

            }

        });
    }

    public static void paymentPatient() {
        List<String> Items = new ArrayList<>();

        Items.add("paid");
        Items.add("Unpaid");
        Items.add("Amount of fine");
        Items.add("return menu");
        BeutifulMenu.showMenu(Items, "Payment", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice) {
                switch (choice) {
                    case 1:
                        PaidPatient();
                        break;

                    case 2:
                        UnpaidPatient();
                        break;

                    case 3:
                        AmountOffine();
                        break;

                    case 4:
                        patientMenu();
                        break;

                    default:
                        PaymentPersonel();

                }
            }

        });

    }

    public static void AmountOffine() {
        //TODO
    }

    public static void UnpaidPatient() {
        List<PatientPayment> patientPayments = context.PatientPayments().getAllUnPaidPayments();
        BeutifulMenu.showPatientPaymentsList(patientPayments, "List of unpaid payment ", new ListCallback() {

            @Override
            public void onItemSelected(int doctorId) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onItemSelected'");
            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onItemSelectedForRemove'");
            }

            @Override
            public void onReturn() {
                paymentPatient();

            }

        });

    }

    public static void PaidPatient() {
        List<PatientPayment> patientPayments = context.PatientPayments().getAllPaidPayments();
        BeutifulMenu.showPatientPaymentsList(patientPayments, "List of paid payment ", new ListCallback() {

            @Override
            public void onItemSelected(int doctorId) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onItemSelected'");
            }

            @Override
            public void onItemSelectedForRemove(int Id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'onItemSelectedForRemove'");
            }

            @Override
            public void onReturn() {
                paymentPatient();

            }

        });
    }

    public static void ratePersonels() {
        //TODO
    }

    public static void visitRequest() {
        BeutifulMenu.getVisitRequest(new CreateVisitRequestCallBack() {

            @Override
            public void onRequestCreated(Visit visit) {
                if (visit != null) {
                    context.Visits().Add(visit);
                }
                patientMenu();

            }

        });

    }

    public static void profilePersonels() {
        switch (LoginInfo.type) {
            case Doctor: {
                Doctor doctor = context.Doctors().getById(LoginInfo.LoginId);
                List<String> Items = new ArrayList<>();
                Items.add("returne menu");
                BeutifulMenu.showDoctorProfile(doctor, Items, new MenuCallback() {

                    @Override
                    public void onMenuSelected(int choice) {
                        switch (choice) {
                            case 1:
                                personelMenu();
                        }

                    }

                });
                break;
            }

            case Nurse: {
                Nurse nurse = context.Nurses().getById(LoginInfo.LoginId);
                List<String> Items = new ArrayList<>();
                Items.add("returne menu");
                BeutifulMenu.showNurseProfile(nurse, Items, new MenuCallback() {

                    @Override
                    public void onMenuSelected(int choice) {
                        switch (choice) {
                            case 1:
                                personelMenu();
                        }

                    }

                });
            }
                break;

            case Personel: {
                Personel personel = context.Personels().getById(LoginInfo.LoginId);
                List<String> Items = new ArrayList<>();
                Items.add("returne menu");
                BeutifulMenu.showPersonelProfile(personel, Items, new MenuCallback() {

                    @Override
                    public void onMenuSelected(int choice) {
                        switch (choice) {
                            case 1:
                                personelMenu();
                        }

                    }

                });

            }
                break;
        }

    }

    public static void OffRequest() {
        //TODO
    }

    public static void PaymentPersonel() {
        //TODO
    }
}
