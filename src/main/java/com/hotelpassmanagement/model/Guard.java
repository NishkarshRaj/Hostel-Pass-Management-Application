package com.hotelpassmanagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hotelpassmanagement.runner.OutPass;
import com.hotelpassmanagement.utilities.Utility;

public class Guard {
    String name;
    long contactnumber;
    int guardID;
    String password;

    public void SetGuard() {
        try {
            Scanner r2 = new Scanner(System.in);
            System.out.print("Enter the name of the Guard: ");
            name = r2.nextLine();
            System.out.print("Enter password: ");
            password = r2.nextLine();
            System.out.print("Enter the Guard ID of the Guard: ");
            guardID = r2.nextInt();
            System.out.print("Enter the contact number of the Guard: ");
            contactnumber = r2.nextLong(); //phone numbers of 10 digits cannot be incorporated inside int variable
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }

    public void show() {
        System.out.println("Guard Details are following: ");
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contactnumber);
        System.out.println("GuardID: " + guardID);
    }

    public String insertionstatement() {
//insert into guard ('name',contactnumber,'guardID','password');
        String inst =
                "insert into guard values ('" + name + "'," + contactnumber + ",'" + guardID + "','" + password + "')";
        return inst;
    }

    public void loginpage() {
        Scanner reader = new Scanner(System.in);
//String defaultpass = "Nishkarsh@123"; //until DB connected
        String n, p, adminpass; //create check for username and password
//adminpass = "12345678" //Extra security for admin interface but easy because guard may not remember difficult password
        int elsech;
        System.out.println("Guard Login Page!!");
        System.out.print("Enter Guard ID: ");
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
            String sql = "select guardid,password from guard where guardid = '" + n + "'";
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
            System.out.println("Invalid Guard ID!");
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
    
    public void menu(String n, String p) {
        int ch;
        Scanner reader = new Scanner(System.in);

        Utility.cls();
        System.out.println("1) Verification of Outing");
        System.out.println("2) Verification of Return");
        System.out.println("3) Logout");
        System.out.print("Enter your choice: ");
        ch = reader.nextInt();
        switch (ch) {
            case 1:
                outdb(n, p);
                try {
                    int ch5;
                    System.out.println("\n\n\n");
                    System.out.println("Enter 1) Return to the homepage");
                    System.out.println("Enter 2) Logout and Exit Application");
                    System.out.println("Enter anything else to exit application");
                    System.out.print("Enter your choice: ");
                    ch5 = reader.nextInt();
                    switch (ch5) {
                        case 1:
                            menu(n, p);
                            break;
                        case 2:
                            Utility.cls();
                            System.exit(0);
                            break;
                        default:
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e);
                    menu(n, p);
                }
//menu(n,p);
                break;
            case 2:
                indb(n, p);
                try {
                    int ch5;
                    System.out.println("\n\n\n");
                    System.out.println("Enter 1) Return to the homepage");
                    System.out.println("Enter 2) Logout and Exit Application");
                    System.out.println("Enter anything else to exit application");
                    System.out.print("Enter your choice: ");
                    ch5 = reader.nextInt();
                    switch (ch5) {
                        case 1:
                            menu(n, p);
                            break;
                        case 2:
                            Utility.cls();
                            System.exit(0);
                            break;
                        default:
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e);
                    menu(n, p);
                }

//menu(n,p);
                break;
            case 3:
                Utility.cls();
                System.exit(0);
                break;
            default:
                Utility.cls();
                System.out.println("Wrong Choice Entered!!!");
                menu(n, p);
        }
    }

    public void outdb(String n, String p) {
//Utility.cls();
    	OutPass obj = new OutPass();
        obj.verification_out();
//menu(n,p);
    }

    public void indb(String n, String p) {
//Utility.cls();
        OutPass obj = new OutPass();
        obj.verification_in();
//menu(n,p);
    }

    public void logout() {
        Utility.cls();
        System.exit(0);
    }
    
    public void createguard() {
        Utility.cls();
        int ch;
        Scanner reader1 = new Scanner(System.in);
        System.out.println("Create a new Guard Account!!!");
        Guard g1 = new Guard();
        g1.SetGuard();
        Utility.cls();
        g1.show();
        System.out.println("\n\n\n");
        System.out.println(
                "Enter 1: Confirm Details and Create Guard!"); //Send to the Database!!! and then directly login!!!
        System.out.println("Enter 2: Discard and redirect to Create Guard Page: ");
        System.out.println("Enter 3: Exit the application: ");
        System.out.print("Enter your choice: ");
        ch = reader1.nextInt();
        switch (ch) {
            case 1:
                Utility.cls();
//insert into guard ('name',contact,'gid','pass');
                try {                     
                    Utility.initDB();
                    Connection con =
                            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
                    Statement st = con.createStatement();
//String inst = "insert into student values ();"
                    String inst;
                    inst = g1.insertionstatement();
//System.out.println(inst);
                    st.executeUpdate(inst);
                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                g1.loginpage();
                break;
            case 2:
                Utility.cls();
                createguard();
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
    
}
