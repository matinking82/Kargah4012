package Utils;

public class LoginInfo {
    public static int LoginId;
    public static loginType type; 

    public enum loginType{
        Admin,
        Nurse,
        Doctor,
        Personel
    }
}
