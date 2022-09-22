package com.hotelpassmanagement;

import java.util.Scanner;

import com.hotelpassmanagement.model.Guard;
import com.hotelpassmanagement.model.Student;
import com.hotelpassmanagement.model.Warden;
import com.hotelpassmanagement.utilities.Utility;

//Using Threads for sleep functionality
class Thread1 extends Thread {
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}



class Main {
    public static void homepage() {
        try {
            int choiceoption1;
            Scanner r = new Scanner(System.in);
            System.out.println("Option Menu!");
            System.out.println("1) Login");
            System.out.println("2) Create Account");
            System.out.println("3) Exit The Application");
            System.out.print("Enter your choice: ");
            choiceoption1 = r.nextInt();
            Utility.cls();
            switch (choiceoption1) {
                case 1:
                    Utility.cls();
                    Login l = new Login();
                    Login.OptionMenu();
                    break;
                case 2:
                    createaccounts();
                    break;
                case 3:
                    System.out.println("Thanks for Using this Application");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option!!! Redirecting to the HomePage");
                    homepage();
            }
        } catch (Exception e) {
            System.out.println(e);
            homepage();
        }
    }


    public static void mainfunction() {
//Declarations
        Scanner reader = new Scanner(System.in);
        Thread1 t = new Thread1();
        char startkey;
//Declarations end
        try {
            Utility.cls();
            System.out.println("\t\t\t\t\t\t\tWelcome to the Hostel Pass Management Application");
            System.out.print("\n\n\n\n\n\t\t\t\t\t\t\t\tPress Any key to continue: ");
            startkey = reader.nextLine().charAt(0);
            Utility.cls();
            homepage();
//Utility.cls();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
            mainfunction();
        } catch (Exception e) {
            System.out.println(e);
            mainfunction();
        }
    }

    public static void main(String args[]) {
        mainfunction();
    }
    
    
    public static void createaccounts() {
        try {
            
            Utility.cls();
            Scanner red5 = new Scanner(System.in);
            int ch5;
            System.out.println("Create Account Page\n\n");
            System.out.println("1) Create Student Account");
            System.out.println("2) Create Warden Account");
            System.out.println("3) Create Guard Account");
            System.out.println("4) Exit Application");
            System.out.print("Enter your choice: ");
            ch5 = red5.nextInt();
            switch (ch5) {
                case 1:
                    System.out.println("Student Account");
                    Student cs = new Student();
                    cs.createstu();
                    break;
                case 2:
                    System.out.println("Warden Account");
                    Warden cw = new Warden();
                    cw.createwarden();
                    break;
                case 3:
                    System.out.println("Guard Account");
                    Guard cg = new Guard();
                    cg.createguard();
                    break;
                case 4:
                    Utility.cls();
                    System.exit(0);
                default:
                    Utility.cls();
                    System.out.println("Wrong Choice!! Redirecting to the Create Account Page");
                    createaccounts();
            }
        } catch (Exception e) {
            System.out.println(e);
            createaccounts();
        }
    }
}