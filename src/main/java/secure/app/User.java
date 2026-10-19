package secure.app;
import java.util.Scanner;

public class User {
    private String usr;
    private String pass;
    private Boolean admin;

    public User(String usr, String pass, Boolean admin) {
        this.usr = usr;
        this.pass = pass;
        this.admin = admin;
    }

    public static User createUser(Scanner s) {
        System.out.println("username:");
        String usr = s.nextLine();

        System.out.println(usr + " added. Password:");
        String pass = s.nextLine();

        System.out.println("password added. Admin? true/false");
        Boolean admin = Boolean.parseBoolean(s.nextLine());

        System.out.println("user added.");
        return new User(usr, pass, admin);
    }

    public String getUsrname() {return usr;}
    public Boolean checkPass(String attempt) { return pass.equals(attempt); }
    public Boolean checkPerms() { return admin; }
}
