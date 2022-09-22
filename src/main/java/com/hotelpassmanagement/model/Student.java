package com.hotelpassmanagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hotelpassmanagement.runner.OutPass;
import com.hotelpassmanagement.utilities.Utility;

public class Student {
    String name;
    int sapid;
    String Course;
    String Room;
    String regnum;
    long contactnumber;
    long guardiancontact;
    String email;
    String password;

    public void SetStudent() {
        try {
            Scanner r2 = new Scanner(System.in);
            System.out.print("Enter the name of the Student: ");
            name = r2.nextLine();
            System.out.print("Enter your Room Number: ");
            Room = r2.nextLine();
            System.out.print("Enter the Course name: ");
            Course = r2.nextLine();
            System.out.print("Enter the Registration Number of the Student: ");
            regnum = r2.nextLine();
            System.out.print("Enter the Email ID of the student: ");
            email = r2.nextLine();
            System.out.print("Enter your password: ");
            password = r2.nextLine();
            System.out.print("Enter SAP ID of the Student: ");
            sapid = r2.nextInt();
            System.out.print("Enter the contact number of the guardian: ");
            guardiancontact = r2.nextLong();
            System.out.print("Enter the contact number of the student: ");
            contactnumber = r2.nextLong(); //phone numbers of 10 digits cannot be incorporated inside int variable
        } catch (InputMismatchException e) {
            Utility.cls();
            System.out.println(e);
            SetStudent();
        } catch (Exception e) {
            Utility.cls();
            System.out.println(e);
            SetStudent();
        }
    }

    public void show() {
        System.out.println("Student Details are following: ");
        System.out.println("Name: " + name);
        System.out.println("Course: " + Course);
        System.out.println("Room Number: " + Room);
        System.out.println("SAP ID: " + sapid);
        System.out.println("Registration Number: " + regnum);
        System.out.println("Contact: " + contactnumber);
        System.out.println("Guardian Contact number: " + guardiancontact);
        System.out.println("Email ID: " + email);
    }

    public String insertionstatement() {
//insert into student values ('name','sapid','password','regnum','Course',contactnumber,guardiancontact,'email','Room');
        String inst =
                "insert into student values ('" + name + "','" + sapid + "','" + password + "','" + regnum + "','" + Course + "'," + contactnumber + "," + guardiancontact + ",'" + email + "','" + Room + "')";
        return inst;
    }

    public void loginpage() {
        Scanner reader = new Scanner(System.in);
//String defaultpass = "Nishkarsh@123"; //until DB connected
        String n, p; //create check for username and password
        int elsech;
        System.out.println("Student Login Page!!");
        System.out.print("Enter Email ID: ");
        n = reader.nextLine();
        System.out.print("Enter Password: ");
        p = reader.nextLine();


//add DB here
        int flag = 0;
        try {
            Utility.initDB();
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
            Statement st = con.createStatement();
            String sql = "select email,password from student where email = '" + n + "'";
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

//if (p.equals("Nishkarsh@123")) //BCD!!! In Java .equals compares value while == is used to compare reference and is
// only meant for objects
        if (flag == 2) {
            Utility.cls();
            menu(n, p);
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
    public void createstu() {
        Utility.cls();
        int ch;
        Scanner reader1 = new Scanner(System.in);
        System.out.println("Create a new Student Account!!!");
        Student s1 = new Student();
        s1.SetStudent();
        Utility.cls();
        s1.show();
        System.out.println("\n\n\n");
        System.out.println(
                "Enter 1: Confirm Details and Create Student!"); //Send to the Database!!! and then directly login!!!
        System.out.println("Enter 2: Discard and redirect to Create Student Page: ");
        System.out.println("Enter 3: Exit the application: ");
        System.out.print("Enter your choice: ");
        ch = reader1.nextInt();
        switch (ch) {
            case 1:
                Utility.cls();
// Adding to the Database!!
// insert into student values ('name','sap','pass','regnum','course',c1,c2,'email','room');
                try {
                    Utility.initDB();
                    Connection con =
                            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
                    Statement st = con.createStatement();
//String inst = "insert into student values ();"
                    String inst;
                    inst = s1.insertionstatement();
//System.out.println(inst);
                    st.executeUpdate(inst);
                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                s1.loginpage();
                break;
            case 2:
                Utility.cls();
                createstu();
                break;
            case 3:
                Utility.cls();
                System.exit(0);
                break;
            default:
                Utility.cls();
                System.out.println("Wrong Choice Entered! Exiting Application");
                System.exit(0);
        }
    }
    
    public void menu(String n, String p) {
        try {
            int choice123;
            Scanner reader123 = new Scanner(System.in);
            Utility.cls();
            System.out.println("1) Check Your Details");
            System.out.println("2) Check Attendance");
            System.out.println("3) Create Outpass");
            System.out.println("4) Check Outpass details");
            System.out.println("5) Logout");
            System.out.print("Enter your choice: ");
            choice123 = reader123.nextInt();
            int s;
            OutPass object11 = new OutPass();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
            Statement st = con.createStatement();
            ResultSet rs;
            switch (choice123) {
                case 1:
                    Utility.cls();
                    String sql5 = "select name,sapid,course from student where email = '" + n + "'";
                    st.executeQuery(sql5);
                    rs = st.executeQuery(sql5);
                    while (rs.next()) {
                        System.out.println("Name: " + rs.getString(1));
                        System.out.println("SAP ID: " + rs.getString(2));
                        System.out.println("Course: " + rs.getString(3));
//System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getInt(6)+rs
// .getInt(7)+rs.getString(8)+rs.getString(9));
                    }
//reader123.nextLine();
//menu(n,p);

                    try {
                        int ch5;
                        System.out.println("\n\n\n");
                        System.out.println("Enter 1) Return to the homepage");
                        System.out.println("Enter 2) Logout and Exit Application");
                        System.out.println("Enter anything else to exit application");
                        System.out.print("Enter your choice: ");
                        ch5 = reader123.nextInt();
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

                    break;
                case 2:
                    Utility.cls();
                    System.out.println("Check Attendance Module to be added later");
                    menu(n, p);
                    break;
                case 3: //Utility.cls();
//System.out.println(n);
                    sql5 = "select name,roomnum,course,sapid,contact,contactp from student where email = '" + n + "'";
//sql5 = "select name from student where email = '"+n+"'";
//System.out.println(sql5);
                    ResultSet rs1 = st.executeQuery(sql5);
                    String s1, s2, s3, s4;
                    long i1, i2;
                    while (rs1.next()) {
                        s1 = rs1.getString(1);
                        s2 = rs1.getString(2);
                        s3 = rs1.getString(3);
                        s4 = rs1.getString(4);
                        i1 = rs1.getLong(5);
                        i2 = rs1.getLong(6);
                        object11.createpass(s1, s2, s3, s4, i1, i2);
                    }
                    try {
                        int ch5;
                        System.out.println("\n\n\n");
                        System.out.println("Enter 1) Return to the homepage");
                        System.out.println("Enter 2) Logout and Exit Application");
                        System.out.println("Enter anything else to exit application");
                        System.out.print("Enter your choice: ");
                        ch5 = reader123.nextInt();
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

//object11.createpass(s1,s2,s3,i4,i5);
//menu(n,p);
                    break;
                case 4:
                    Utility.cls();
                    System.out.print("Re-enter your SAP Id for verification: ");
                    s = reader123.nextInt();
                    String sql = "select permission from outpass where sapid =" + s + "";
                    st.executeQuery(sql);
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                    }
                    object11.NotifyStudent(s);
                    menu(n, p);
                    break;
                case 5:
                    Utility.cls();
                    System.exit(0);
                    break;
                default:
                    Utility.cls();
                    System.out.println("Invalid Options Entered!!! Redirected to Main Menu");
                    menu(n, p);
            }
            con.close();
        } catch (InputMismatchException e) {
            System.out.println(e);
            menu(n, p);
        } catch (Exception e) {
            System.out.println(e);
            menu(n, p);
        }
    }
}