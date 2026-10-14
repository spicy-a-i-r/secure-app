package secure.app;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        int choice = 0;
        String usr = "";
        String pass = "";

        while (choice != 3) {

            System.out.println("Would you like to:\n1. Set up an account\n2. login\n3. Quit");
            choice = reader.nextInt();

            if (choice == 1) {
                if (usr == "") {
                    System.out.println("Enter username: "); // these two functions go off at the same time which screws the password collection
                    usr = reader.nextLine(); 
                    System.out.println("Enter password: ");
                    pass = reader.nextLine();
                }
                
                System.out.println("Account has already been made");

            }

            else if (choice == 2) {
                Boolean login = false;

                while (!login) {
                    System.out.println("enter username");
                    String usrAttempt = reader.nextLine();
                    System.out.println("enter password: ");
                    String passAttempt = reader.nextLine();   

                    if (usrAttempt == usr && passAttempt == pass) {
                        System.out.println("login successful");
                        login = true;
                    }

                    System.out.println("incorrect credientials, try again");
                }
                
            }

            else if (choice == 3) {
                System.out.println("Have a good day!");
                return;
            }

            System.out.println("please type a valid choice");

        }

        reader.close();
    }

}