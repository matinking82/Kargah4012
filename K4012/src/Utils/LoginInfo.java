package Utils;

public class LoginInfo {
    public static boolean IsLogged = false;
    public static int LoginId;
    public static loginType type;

    public static void reset() {
        IsLogged = false;
        LoginId = 0;
        type = null;
    }

    public enum loginType {
        Admin,
        Nurse,
        Doctor,
        Personel,
        Patient
    }
}
