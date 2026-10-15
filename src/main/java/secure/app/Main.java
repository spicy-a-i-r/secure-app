package secure.app;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;



public class Main {
    Dictionary<String, String> s = new Hashtable<>();

    public   void display() {
        Scanner r = new Scanner(System.in); // r is the reader

        int choice = 0;

        while (choice != 3) {
            System.out.println("1. Setup\n2. Login\n3. Quit.");
            choice = r.nextInt();

            if (choice == 1) {
                createAccount();
                choice = 0;
            }
            else if (choice == 2) {
                login();
                choice = 0;
            }
            else if (choice == 3) {
                System.out.println("Thank you!");
            }
        }

        r.close(); // remember to close it

    }

    public void createAccount() {
        
        Scanner u = new Scanner(System.in);
        Scanner p = new Scanner(System.in);

        System.out.println("username? ");
        String usr = u.nextLine();
        
        System.out.println("password?");
        String pass = p.nextLine();
        s.put(usr, pass);
        System.out.println("added.");

        u.close(); p.close();


        // return true;
    }

    public static void login() {

        // return true;
    }


    public static void main(String[] args) {

        display();
    }   

}