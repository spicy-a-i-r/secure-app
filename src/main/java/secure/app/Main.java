package secure.app;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;




public class Main {

    public static Scanner s = new Scanner(System.in);


    public static void encrypt(char[] pass) throws Exception{
        // simple usr interface


        System.out.println("enter the path of the file you want encrypted");

        String input = s.nextLine();
        Path input_file = Paths.get(input);
    
        String output = "placeholder";

        /////////////////////////////////////////////////////////////////
        
        // find the secure folder
        Path currentDir = Path.of("").toAbsolutePath(); 
        File[] files = currentDir.toFile().listFiles();

        if (files == null) { // althought i am getting files
            System.out.println("no files here");
            return;
        } 

        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().equals("storage")) { 
                    String name = input_file.getFileName().toString();
                    String path = file.toString();

                    File f = new File(path + name);
                    try {
                        if (f.createNewFile()) {
                            output = f.getAbsolutePath();
                        } else{ System.out.println("file already exists."); return; }
                    } catch (IOException e) { e.printStackTrace(); }
                }
            }
            
        }


        Path out = Paths.get(output);

        ///////////////////////////////////////////////////////////////////////

        // encrypt the file and delete the old one




        FileCrypto.encrypt(input_file,out, pass);
        File f = new File(input);
        f.delete();
    }

    public static void decrypt(char[] pass) throws Exception{
        // usr interface

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
                return;
            }
        } catch (IOException e){
            System.out.println("an error occured");
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception{
        Path currentDir = Path.of("").toAbsolutePath(); 
        File[] files = currentDir.toFile().listFiles();

        System.out.println(files);


        System.out.println("Create user? y/n");
        String choice = s.nextLine(); 


        if (choice == "n") { // this straight up doesnt work
            System.out.println("Good bye!");
            return;
        } 

        User usr = User.createUser(s);

        // ask to enter password

        System.out.println("please enter your password");
        String pass_attempt = s.nextLine();

        if (usr.checkPass(pass_attempt)) {
            char[] pass = pass_attempt.toCharArray();
            System.out.println("logged in.\n would you like to 1. encrypt\n2. decrypt\n3. leave");
            int c = s.nextInt();
            s.nextLine(); // next int does not clear the next line
            

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
                System.out.println("please enter correct option"); // for some reason this is spinning up
            }


        }

    }
}