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
                    String path = file.getAbsolutePath().toString();

                    File f = new File(path + "\\" + name);
                    try {
                        if (f.createNewFile()) {
                            output = f.getAbsolutePath();
                        } else{ System.out.println("file already exists."); return; }
                    } catch (IOException e) { e.printStackTrace(); }
                }
            }
            
        }


        Path out = Paths.get(output); System.out.println(out);




        FileCrypto.encrypt(input_file,out, pass);
        File f = new File(input);
        f.delete();

        return;
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
            char[] pass = pass_attempt.toCharArray();
            int c = 0;
            

            while (c != 3) {
                System.out.println("would you like to..\n 1. encrypt\n2. decrypt\n3. leave");
                c = s.nextInt();
                s.nextLine(); // next int does not clear the next line 

                if (c == 1) {
                    encrypt(pass);
                } else if (c == 2) {
                    if (usr.checkPerms()) { decrypt(pass); }
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