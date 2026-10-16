package secure.app;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void createFile() {
        try {
            File f = new File("text.txt");
            if (f.createNewFile()) {
              System.out.println("new file created. " + f.getName());  
            }
            else {
            System.out.println("file already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    public static void addUsr(String usr) {
        try {
            FileWriter w = new FileWriter("text.txt");
            w.write(usr);
            w.close();
            System.out.println(usr + " added");
        } catch (IOException e) {
            System.out.println("error creating file");
            e.printStackTrace();
        }
    }



    public static String display() {
        Scanner s = new Scanner(System.in);

        System.out.println("username?");
        String usrname = s.nextLine();

        
        s.close();
        return usrname;
        
    }

    public static void deleteFile() {
        File f = new File("text.txt");
        if (f.delete()) {
            System.out.println("file deleted");
        } else {
            System.out.println("error deleting file");
        }
    }


    public static void main(String[] args) {
        createFile();
        addUsr(display());

        Scanner s = new Scanner(System.in);
        System.out.println("delete file? 0 / 1");

        if (s.hasNextInt()) {
           int choice = s.nextInt(); 
        }
        
        
        while (choice != 0)
            if (choice == 1) {
                deleteFile();
            }
            else if (choice == 0) {
                System.out.println("Program complete");
                s.close();
                return;
            }
            else {
                System.out.println("invalid choice");
            }
        s.close();
    }
}