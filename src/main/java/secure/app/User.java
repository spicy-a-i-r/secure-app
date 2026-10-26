package secure.app;
import java.util.Scanner;

public class User {
    private String usr;
    private String pass;
    private Boolean admin;
    
    private static String getUsername(Scanner s) {
        System.out.println("Please enter your username. Between 5-15 characters");
        String input = s.nextLine();

        if (input.length() > 15 || input.length() < 5) {
            System.out.println("incorrect length.");
            getUsername(s);
        }
        return input;
    }

    private static String getPass(Scanner s) {
        System.out.println("please enter password between 5-25 characters");
        String input = s.nextLine();

        if (input.length() < 5 || input.length() > 25) {
            System.out.println("incorrect length");
            getPass(s);
        }

        System.out.println("are you sure you want to save this password? yes/no");
        String conf = s.nextLine();
        Boolean c = (conf.equals("yes")) ? true : false;


        if (!c) { getPass(s); }
        System.out.println("password added. ");
        return input;
    }

    private static Boolean getAdmin(Scanner s) { // change to automatically check
        System.out.println("Are you a admin?");
        Boolean admin = Boolean.parseBoolean(s.nextLine());
        if (admin) {System.out.println("admin added."); return admin;}
        System.out.println("user added."); 

        return admin;
    }

    public User(String usr, String pass, Boolean admin) {
            this.usr = usr;
            this.pass = pass;
            this.admin = admin;
    }

    public static User createUser(Scanner s) {
            String usr = getUsername(s);
            System.out.println(usr + " added.");

            String pass = getPass(s);

            Boolean admin = getAdmin(s);
            return new User(usr, pass, admin);

        }
    
    public String getUsrname() {return usr;}
    public Boolean checkPass(String attempt) { return pass.equals(attempt); }
    public Boolean checkPerms() { return admin; }
}
