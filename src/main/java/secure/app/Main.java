package secure.app;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;




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

    public static void encrypt(char[] pass) throws Exception{
        // simple usr interface

        Scanner s = new Scanner(System.in);

        System.out.println("enter the path of the file you want encrypted");
        Path input = Paths.get(s.nextLine());
        
        
        String output = "placeholder";

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
        Path out = Paths.get(output);

        ///////////////////////////////////////////////////////////////////////

        // encrypt the file and delete the old one


        File f = new File(input.toString());
        f.delete();

        FileCrypto.encrypt(input,out, pass);
        s.close();
    }

    public static void decrypt(char[] pass) throws Exception{
        // usr interface
        Scanner s = new Scanner(System.in);

        System.out.println("What is the name of the file? ");
        Path input = Paths.get(s.nextLine());

        try {
            File f = new File(input.toFile().getName());
            if (f.createNewFile()) {
                Path output = Paths.get(f.getAbsolutePath());
                FileCrypto.decrypt(input, output, pass);
                System.out.println("File decrypted at " + output);
                
                File i = new File(input.toString());
                i.delete();
            }
            else {
                System.out.println("Error decrypting file (already decrypted)");
                s.close();
                return;
            }
        } catch (IOException e){
            System.out.println("an error occured");
            e.printStackTrace();
        }
        s.close();

    }

    public static void main(String[] args) throws Exception{


        Scanner s = new Scanner(System.in);
        System.out.println("Create user? y/n");
        String choice = s.nextLine();

        if (choice != "y") {
            System.out.println("Good bye!");
            return;
        } 

        User usr = createUser(s);

        // ask to enter password

        System.out.println("please enter your password");
        String pass_attempt = s.nextLine();

        if (usr.checkPass(pass_attempt)) {
            char[] pass = pass_attempt.toCharArray();
            System.out.println("logged in.\n would you like to 1. encrypt\n2. decrypt\n3. leave");
            int c = s.nextInt();
            

            while (c != 3) { // convert to short hand
                if (c == 1) {
                    encrypt(pass);
                } else if (c == 2) {
                    decrypt(pass);
                } else if (c==3) {
                    System.out.println("GoodBye!");
                    s.close();
                    return;
                }
                System.out.println("please enter correct option");
            }


        }

    }
}