package secure.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;



public class Main {



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