import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DbContext.DataBaseContext;
import DbContext.Interfaces.IDataBaseContext;
import Utils.BeutifulMenu;
import Utils.BeutifulMenu.MenuCallback;

public class App {
    protected static final String ctors = null;
    public static void main(String[] args) throws Exception {
        
        mainMenu();
        
    }

    public static void mainMenu() {
        List<String> Items=new ArrayList<>();
        Items.add("Admin panel");
        Items.add("Personel panel");
        Items.add("Patient panel");
        BeutifulMenu.showMenu(Items ,"Main menu",new MenuCallback() {
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:adminMenu();
                    break;
        
                    case 2:personelMenu();
                    break;
        
                    case 3:patientMenu();
                    break;
        
                    default:mainMenu();
                }
            }
        });
    }
    public static void patientMenu()
    {
        List<String> Items=new ArrayList<>();
        Items.add("visit Request");
        Items.add("rate Personels");
        Items.add("Payment");
        BeutifulMenu.showMenu(Items,"patient panel" , new MenuCallback() {
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:visitRequest();
                    break;
        
                    case 2:ratePersonels();
                    break;
        
                    case 3:paymentPatient();
                    break;
        

        
                    default:personelMenu();
                }

            }
        });


    }
    public static void personelMenu()
    {
        List<String> Items=new ArrayList<>();
        Items.add("Profile");
        Items.add("Off Request");
        Items.add("Payment");

        BeutifulMenu.showMenu(Items, "Personel panel", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:profilePersonels();
                    break;
        
                    case 2:OffRequest();
                    break;
        
                    case 3:PaymentPersonel();
                    break;
        
                    default:personelMenu();
        
                }

            }
        });



    }
    public static void adminMenu()
    {
        List<String> Items=new ArrayList<>();
        Items.add("Manage Personel");
        Items.add("Manange Patient");
        Items.add("visits");
        Items.add("Hospitalization");
        Items.add("Profile");
        Items.add("Payment");
        BeutifulMenu.showMenu(Items, "Admin panel", new MenuCallback() {
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:managePersonel();
                    break;
        
                    case 2:manangePatient();
                    break;
        
                    case 3:visits();
                    break;
        
                    case 4:hospitalization();
                    break;
        
                    case 5: ProfileAdmin();
                    break;

                    case 6:Payment();
                    break;
        
                    default: adminMenu();
                }  

            }
        });
    }
    


    
    public static void Payment() {
        List<String> Items=new ArrayList<>();
        Items.add("paid");
        Items.add("Unpaid");
        BeutifulMenu.showMenu(Items, "Payment",new MenuCallback() {
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:paid();
                    break;

                    case 2:Unpaid();
                    break;

                    default:Payment();
                    
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
        List<String> Items=new ArrayList<>();
        Items.add("Add hospitalizations");
        Items.add("List of hospitalizations");
        BeutifulMenu.showMenu(Items, "hospitalizatio",new MenuCallback() {
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:Addhospitalizations();
                    break;

                    case 2:ListOfHospitalizations();
                    break;

                    default:hospitalization();
                }  

            }
        });


    }
    public static void ListOfHospitalizations() {
    }

    public static void Addhospitalizations() {
    }

    public static void visits() {
        List<String> Items=new ArrayList<>();
        Items.add("List of visits ");
        Items.add("add visit");
        BeutifulMenu.showMenu(Items,"visit" ,new MenuCallback() {
        @Override
        public void onMenuSelected(int choice){
            switch(choice)
            {
                case 1:ListOfVisits();
                break;

                case 2:addvisit();
                break;

                default:visits();

                
            }  

        }
    });
}


    public static void addvisit() {
    }

    public static void ListOfVisits() {
    }


    
    public static void manangePatient() {
    
        List<String> Items=new ArrayList<>();
        Items.add("List Of Patient");
        Items.add("Add Patient");
        Items.add("");
        BeutifulMenu.showMenu(Items ,"Manage patient",new MenuCallback() {
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:ListOfPatient();
                    break;

                    case 2:AddPatient();
                    break;

                    default:manangePatient();


                }  

            }
        });



    }
    public static void AddPatient() {
    }

    public static void ListOfPatient() {
    }

    public static void managePersonel() {
        
     List<String> Items=new ArrayList<>();
     Items.add("Doctors");
     Items.add("Nurses");
     Items.add("Add a new Personel");
     Items.add("List Of Personels");
     BeutifulMenu.showMenu(Items, "Manage personel",new MenuCallback(){
        @Override
        public void onMenuSelected(int choice){
            switch(choice)
            {
                case 1:Doctors();
                break;
    
                case 2:Nurses();
                break;
    
                case 3:AddPersonels();
                break;
    
                case 4:ListPersonels();
                break;
                
                default: adminMenu();
            } 
        }
    });

}

     
    public static void ListPersonels() {
    }

    public static void AddPersonels() {

    } 

    public static void Nurses() {
        List<String> Items=new ArrayList<>();
        Items.add("List Of Nurses");
        Items.add("Add Nurses");
        BeutifulMenu.showMenu(Items, "Nurses",new MenuCallback(){
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:ListOfNurses();
                    break;

                    case 2:AddNurses();
                    break;
                    
                    default: adminMenu();
                } 
            }

        });

    }
        
    public static void AddNurses() {
    }

    public static void ListOfNurses() {
    }

    public static void Doctors() {
        List<String> Items=new ArrayList<>();
        Items.add("List Of Doctors");
        Items.add("Add Doctors");
        BeutifulMenu.showMenu(Items, "Doctors",new MenuCallback(){
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:ListOfDoctors();
                    break;

                    case 2:{
                        addDoctors();
                    }
                    break;
                    
                    default: adminMenu();
                } 
            }

        });
        
    }

    public static void addDoctors()
    {

    }

    public static void ListOfDoctors() {

    }


    public static void paymentPatient() {
        List<String> Items=new ArrayList<>();

        Items.add("paid");
        Items.add("Unpaid");
        Items.add("Amount of fine");
        BeutifulMenu.showMenu(Items ,"Payment",new MenuCallback(){
            @Override
            public void onMenuSelected(int choice){
                switch(choice)
                {
                    case 1:PaidPatient();
                    break;

                    case 2:UnpaidPatient();
                    break;

                    case 3:AmountOffine();
                    break;

                    default:PaymentPersonel();


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
    public static void profilePersonels()
    {

    }
    public static void OffRequest()
    {
    

    }
    public static void PaymentPersonel()
    {



    }
}

