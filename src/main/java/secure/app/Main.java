package secure.app;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;




public class Main {

    public static Scanner s = new Scanner(System.in);


    public static void encrypt(String p, char[] pass) throws Exception{
        // simple usr interface


        System.out.println("enter the path of the file you want encrypted");

        String input = s.nextLine();
        Path input_file = Paths.get(input);
    
        Path output = null;
        
        try {
            File f;
            String name = input_file.getFileName().toString();
            File processed = new File(p);
            File[] files = processed.listFiles();

            for (File content : files) {
                if (content.getName().equals(name)) {
                    System.out.println("files already exists"); return;
                }
            }
            
            f = new File(processed + "\\" + name);
            if (f.createNewFile()) {
                output = Paths.get(f.getAbsolutePath());
            } else { System.out.println("error creating the file"); return; } 
        } catch (IOException e) { e.printStackTrace(); }






        FileCrypto.encrypt(input_file,output, pass);
        File f = new File(input);
        f.delete();

        return;
    }

    public static void decrypt(String path, char[] pass) throws Exception{
        // usr interface

        System.out.println("What is the name of the file? ");
        String fileName = "\\" + s.nextLine();
        Path input = Paths.get(path + fileName);

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

        System.out.println("Create user? yes/no");
        String choice = s.nextLine(); 


        if (!choice.equals("yes")) { 
            System.out.println("Good bye!");
            return;
        } 

        User usr = User.createUser(s);

        System.out.println("please enter your password");
        String pass_attempt = s.nextLine();

        if (usr.checkPass(pass_attempt)) {
            String path = Setup.getFileFolder();
            char[] pass = pass_attempt.toCharArray();
            int c = 0;
            

            while (c != 3) {
                System.out.println("would you like to..\n 1. encrypt\n2. decrypt\n3. leave");
                c = s.nextInt();
                s.nextLine(); // next int does not clear the next line 

                if (c == 1) {
                    encrypt(path,pass);
                } else if (c == 2) {
                    if (usr.checkPerms()) { decrypt(path, pass); }
                    System.out.println("you do not have permissions to decrypt files");
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