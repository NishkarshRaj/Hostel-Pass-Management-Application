package com.hotelpassmanagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hotelpassmanagement.runner.OutPass;
import com.hotelpassmanagement.utilities.Utility;

public class Warden {
    String name;
    long contactnumber;
    String email;
    String password;

    public void SetWarden() {
        try {
            Scanner r2 = new Scanner(System.in);
            System.out.print("Enter the name of the Warden: ");
            name = r2.nextLine();
            System.out.print("Enter your password: ");
            password = r2.nextLine();
            System.out.print("Enter the Email ID of the Warden: ");
            email = r2.nextLine();
            System.out.print("Enter the contact number of the Warden: ");
            contactnumber = r2.nextLong(); //phone numbers of 10 digits cannot be incorporated inside int variable
            password = "Nishkarsh@123";
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }

    public void show() {
        System.out.println("Warden Details are following: ");
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contactnumber);
        System.out.println("Email ID: " + email);
    }

    public String insertionstatement() {
        String inst =
                "insert into warden values ('" + name + "'," + contactnumber + ",'" + email + "','" + password + "')";
        return inst;
    }

    public void loginpage() {
        Scanner reader = new Scanner(System.in);
        String n, p, adminpass; //create check for username and password
        int elsech;
        System.out.println("Warden Login Page!!");
        System.out.print("Enter Email ID: ");
        n = reader.nextLine();
        System.out.print("Enter Password: ");
        p = reader.nextLine();
        System.out.print("Enter the Administrator Password: ");
        adminpass = reader.nextLine();

        int flag = 0;
        try {
            Utility.initDB();
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
            Statement st = con.createStatement();
            String sql = "select email,password from warden where email = '" + n + "'";
//System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                flag = 1;
                if (p.equals(rs.getString(2))) {
                    flag = 2;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        if (flag == 2) {
            if (adminpass.equals("Nishkarsh@123")) {
                menu(n, p);
            } else {
                System.out.println("Wrong Admin Password");
                System.out.println("Enter 1 to redirect to login page!");
                System.out.println("Enter 2 to exit the application!!");
                System.out.print("Enter your choice: ");
                elsech = reader.nextInt();
                if (elsech == 1) {
                    Utility.cls();
                    loginpage();
                } else if (elsech == 2) {
                    Utility.cls();
                    System.exit(0);
                } else {
                    Utility.cls();
                    System.out.println("Wrong Choice Entered!!! Exiting the application!");
                    System.exit(0);
                }
            }
        } else if (flag == 1) {
            System.out.println("Invalid Password!");
            System.out.println("Enter 1 to redirect to login page!");
            System.out.println("Enter 2 to exit the application!!");
            System.out.print("Enter your choice: ");
            elsech = reader.nextInt();
            if (elsech == 1) {
                Utility.cls();
                loginpage();
            } else if (elsech == 2) {
                Utility.cls();
                System.exit(0);
            } else {
                Utility.cls();
                System.out.println("Wrong Choice Entered!!! Exiting the application!");
                System.exit(0);
            }
        } else {
            System.out.println("Invalid Email!");
            System.out.println("Enter 1 to redirect to login page!");
            System.out.println("Enter 2 to exit the application!!");
            System.out.print("Enter your choice: ");
            elsech = reader.nextInt();
            if (elsech == 1) {
                Utility.cls();
                loginpage();
            } else if (elsech == 2) {
                Utility.cls();
                System.exit(0);
            } else {
                Utility.cls();
                System.out.println("Wrong Choice Entered!!! Exiting the application!");
                System.exit(0);
            }
        }
    }
    
    
    public void createwarden() {
        Utility.cls();
        int ch;
        Scanner reader1 = new Scanner(System.in);
        System.out.println("Create a new Warden Account!!!");
        Warden w1 = new Warden();
        w1.SetWarden();
        Utility.cls();
        w1.show();
        System.out.println("\n\n\n");
        System.out.println(
                "Enter 1: Confirm Details and Create Warden!"); //Send to the Database!!! and then directly login!!!
        System.out.println("Enter 2: Discard and redirect to Create Warden Page: ");
        System.out.println("Enter 3: Exit the application: ");
        System.out.print("Enter your choice: ");
        ch = reader1.nextInt();
        switch (ch) {
            case 1:
                Utility.cls();
                try {                    
                    Utility.initDB();
                    Connection con =
                            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
                    Statement st = con.createStatement();
                    String inst;
                    inst = w1.insertionstatement();
//System.out.println(inst);
                    st.executeUpdate(inst);
                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                w1.loginpage();
                break;
            case 2:
                Utility.cls();
                createwarden();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                Utility.cls();
                System.out.println("Wrong Choice Entered! Exiting Application");
                System.exit(0);
        }
    }
    public void menu(String n, String p) { 
    	Scanner reader = new Scanner(System.in);
    	int ch;

        try {
            Utility.cls();
            System.out.println("Warden Home Page\n\n");
            System.out.println("1) Check for new OutPass Requests");
            System.out.println("2) Check Student Attendance");
            System.out.println("3) Logout");
            System.out.print("Enter your choice: ");
            ch = reader.nextInt();
            switch (ch) {
                case 1:
                    Utility.cls();
                    permissions(n, p);
                    break;
                case 2:
                    Utility.cls();
                    attendance(n, p);
                    break;
                case 3:
                    Utility.cls();
                    logout();
                    break;
                default:
                    Utility.cls();
                    System.out.println("Wrong Choice Entered!! Redirected to the main menu!!!");
                    menu(n, p);
            }
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }

    public void permissions(String n, String p) {
        Utility.cls();
        OutPass obj = new OutPass();
        obj.permission();
        menu(n, p);
    }

    public void attendance(String n, String p) {
        Utility.cls();
        System.out.println("Checking the attendance from DB");
        menu(n, p);
    }

    public void logout() {
        Utility.cls();
        System.exit(0);
    }
}
