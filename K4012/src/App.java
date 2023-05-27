import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DbContext.DataBaseContext;
import DbContext.Interfaces.IDataBaseContext;
import Models.Doctor;
import Models.Nurse;
import Models.Patient;
import Models.Personel;
import Utils.BeutifulMenu;
import Utils.BeutifulMenu.CreateDoctorCallBack;
import Utils.BeutifulMenu.CreateNurseCallBack;
import Utils.BeutifulMenu.CreatePatientCallBack;
import Utils.BeutifulMenu.CreatePersonelCallBack;
import Utils.BeutifulMenu.ListCallback;
import Utils.BeutifulMenu.MenuCallback;

public class App {
    protected static final String ctors = null;
    private static IDataBaseContext context;

    public static void main(String[] args) throws Exception {
        context = new DataBaseContext();
        mainMenu();

    }

    public static void mainMenu() {
        List<String> Items = new ArrayList<>();
        Items.add("Admin panel");
        Items.add("Personel panel");
        Items.add("Patient panel");
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

                    default:
                        mainMenu();
                }
            }

        });
    }

    public static void patientMenu() {
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

    public static void adminMenu() {
        List<String> Items = new ArrayList<>();
        Items.add("Manage Personel");
        Items.add("Manange Patient");
        Items.add("visits");
        Items.add("Hospitalization");
        Items.add("Profile");
        Items.add("Payment");
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
                        mainMenu();
                        break;

                    default:
                        adminMenu();
                }

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
    }

    public static void Addhospitalizations() {
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
    }

    public static void ListOfVisits() {
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
        List<Patient> patients   = context.Patients().getAllPatientsList();
        BeutifulMenu.showPatientsList(patients, "List of Patients", new ListCallback() {
            @Override
            public void onItemSelected(int id) {
                Patient patient=context.Patients().getById(id);
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
        List<Personel> personels   = context.Personels().getAllPersonelsList();
        BeutifulMenu.showPersonelsList(personels, "List of Personels", new ListCallback() {
            @Override
            public void onItemSelected(int id) {
                Personel personel=context.Personels().getById(id);
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
        List<Nurse> nurses  = context.Nurses().getAllNursesList();
        BeutifulMenu.showNursesList(nurses, "List of Nurses", new ListCallback() {
            @Override
            public void onItemSelected(int id) {
                Nurse nurse=context.Nurses().getById(id);
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
                Doctor doctor=context.Doctors().getById(id);
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

    }

    public static void UnpaidPatient() {
    }

    public static void PaidPatient() {
    }

    public static void ratePersonels() {

    }

    public static void visitRequest() {

    }

    public static void profilePersonels() {

    }

    public static void OffRequest() {

    }

    public static void PaymentPersonel() {

    }
}
