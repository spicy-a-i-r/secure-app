package secure.app;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

    public void encryptFile(String file) {

        Boolean isAdmin = User.checkPerms();
        // first find the path
        // check is file is already encrypted
        // if not then encypt it
    }

    public static void readFile(String file) {
        //check if file is encrypted
            // check the priviledges
        //decrypt the file
        //return the file
    }

    public static void decryptFile(String file) {
        // check if the file is encrypted
        // if so then check privileges
        // decrypt file
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

    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        System.out.println("Create user? y/n");
        String choice = s.nextLine();

        if (choice != "y") {
            System.out.println("Good bye!");
            return;
        } 

        createUser(s);




    }
}