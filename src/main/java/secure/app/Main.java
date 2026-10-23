package secure.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;




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

    public static void encrypt(String pass) {
        // simple usr interface

        Scanner s = new Scanner(System.in);

        System.out.println("enter the path of the file you want encrypted");
        String input = s.nextLine();
        
        String output;

        /////////////////////////////////////////////////////////////////
        
        // find the secure folder
        Path currentDir = Path.of("").toAbsolutePath();
        File[] files = currentDir.toFile().listFiles();

        if (files == null) {
            System.out.println("no files here");
            s.close();
            return;
        }

        for (File file : files) {
            if (file.isDirectory() && file.getName() == "storage") {
                output = file.getAbsolutePath();
            } else {
                System.out.println("no storage found");
                s.close();
                return;
            }
            
        }

        ///////////////////////////////////////////////////////////////////////

        // encrypt the file and delete the old one

        FileCrypto encrypt(input,output, pass);

        input.delete(); 

        s.close();
    }

    public static void decrypt() {
        // usr interface

        // find the secure folder

        // call the decrypt function
        
        // delete the encrypted file and create a new file
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