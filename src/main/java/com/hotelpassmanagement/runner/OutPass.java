package com.hotelpassmanagement.runner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hotelpassmanagement.utilities.Utility;

public class OutPass {
    //Specifying Data Members
    String name;
    String room;
    String course;
    long contact;
    long contactp;
    String leavedate; //specified by student !!! Try to use Date and Time Utility
    String returndate; //specified by student
    String address; //Where you are going
    String sap;

    //creating a constructor
    public OutPass() {
        sap = "";
        name = "";
        room = "";
        course = "";
        contact = 0000;
        contactp = 0000;
        leavedate = "";
        returndate = "";
        address = "";
    }

    public void createpass(String name1, String room1, String course1, String sap1, long contact1,
                           long contactp1) //used by student
    {
        try {
            int ch;
            
            Utility.cls();
            System.out.println("Create Out Pass Module\n\n");
            Scanner reader = new Scanner(System.in);
            name = name1;
            room = room1;
            sap = sap1;
            course = course1;
            contact = contact1;
            contactp = contactp1;
            System.out.print("Enter the Leave Date: ");
            leavedate = reader.nextLine();
            System.out.print("Enter the Return Date: ");
            returndate = reader.nextLine();
            System.out.print("Specify the Leave Address: ");
            address = reader.nextLine();
            show();
            System.out.println("Enter 1: Confirm Details and Submit OutPass Request");
            System.out.println("Enter 2: Discard and Recreate your Pass");
            System.out.println("Enter 3: Exit the Application");
            System.out.print("Enter your choice: ");
            ch = reader.nextInt();
            switch (ch) {
                case 1:
                	Utility.initDB();
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection con = DriverManager
                                .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
                        Statement st = con.createStatement();
                        String no = "n";
                        String sql =
                                "insert into outpass values('" + sap1 + "','" + name1 + "','" + course1 + "'," + contact1 + "," + contactp1 + ",'" + room + "','" + leavedate + "','" + returndate + "','" + no
                                        .charAt(0) + "','" + no.charAt(0) + "','" + no.charAt(0) + "')";
//System.out.println(sql);
                        st.executeUpdate(sql);
                        con.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    NotifyParent(contactp1);
                    NotifyWarden();
//Main m = new Main();
//m.homepage();
                    break;
                case 2:
                    Utility.cls();
                    createpass(name1, room1, course1, sap1, contact1, contactp1);
                    break;
                case 3:
                    Utility.cls();
                    System.exit(0);
                default:
                    Utility.cls();
                    System.out.println("Wrong Choice Entered!!! Redirected to the Create Pass Menu\n\n");
                    createpass(name1, room1, course1, sap1, contact1, contactp1);
            }
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
    }

    public void show() //called by createpass()
    {
        Utility.cls();
        System.out.println("Verify your details for the current OutPass\n");
        System.out.println("Name: " + name);
        System.out.println("SAP ID: " + sap);
        System.out.println("Room Number: " + room);
        System.out.println("Course: " + course);
        System.out.println("Contact: " + contact);
        System.out.println("Guardian Contact: " + contactp);
        System.out.println("Leave Date: " + leavedate);
        System.out.println("Return Date: " + returndate);
        System.out.println("Leave Address: " + address);
    }

    public void NotifyParent(long contact123) //called by createpass()
    {
        System.out.println("SMS sent to your parent/guardian about OutPass at the number: " + contact123);
    }

    public void NotifyWarden()  //called by createpass()
    {
        System.out.println("Triggered Notification to the Warden. Waiting for permission");
    }

    public void permission() //used by warden
    {
//Utility.cls();
        try {
            int sap123;
            String ch123;
            Scanner redder = new Scanner(System.in);
            Utility.initDB();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con =
                        DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
                Statement st = con.createStatement();
                String sql = "select * from outpass where permission in ('n','N')";
//System.out.println("Check 1");
                ResultSet rs = st.executeQuery(sql);
//System.out.println("Check 2"); 
                while (rs.next()) {
//System.out.println("Check 3");
//System.out.println(rs.getString(1) + rs.getString(2) +rs.getString(3) +rs.getString(4) +rs.getString(5) +rs
// .getString(6) +rs.getString(7) +rs.getString(8) +rs.getString(9) +rs.getString(10) +rs.getString(11));
                    System.out.println("SAP ID: " + rs.getString(1));
                    System.out.println("Name: " + rs.getString(2));
                    System.out.println("Course: " + rs.getString(3));
                    System.out.println("Contact: " + rs.getString(4));
                    System.out.println("Parent Contact: " + rs.getString(5));
                    System.out.println("Room Number: " + rs.getString(6));
                    System.out.println("Leave Date: " + rs.getString(7));
                    System.out.println("Return Date: " + rs.getString(8));
//System.out.println(": " + rs.getString(9));
//System.out.println(": " + rs.getString(10));
//System.out.println(": " + rs.getString(11));
                    System.out.println("\n\n");
                }
                System.out.print("Enter Sapid to grant permission: ");
                sap123 = redder.nextInt();
                System.out.println("Enter the permission as G(granted) or D(Denied)");
                redder.nextLine();
                ch123 = redder.nextLine();
                if (ch123.equals("D")) {
                    String sql1 = "update outpass set permission = 'd' where sapid ='" + sap123 + "'";
                    st.executeUpdate(sql1);
                } else if (ch123.equals("G")) {
                    String sql1 = "update outpass set permission = 'g' where sapid ='" + sap123 + "'";
                    st.executeUpdate(sql1);
                } else {
                    permission();
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
                permission();
            }
        } catch (InputMismatchException e) {
            System.out.println(e);
            permission();
        }
    }

    public void NotifyStudent(int sapid) {
        Utility.cls();
        try {
            Utility.initDB();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
            Statement st = con.createStatement();
            String sql = "select permission from outpass where sapid = " + sapid + "";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void verification_out() //used by guard
    {
        try {
            Utility.initDB();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
            Statement st = con.createStatement();
            String sql = "select sapid from outpass where vout in ('n','N')";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            int sap123;
            Scanner reader = new Scanner(System.in);
            System.out
                    .print("Enter the Sapid whose Verification you want to perform (Press 1 to return to homepage): ");
            sap123 = reader.nextInt();
            String sql1 = "update outpass set vout = 'y' where sapid = '" + sap123 + "'";
            st.executeUpdate(sql1);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void verification_in() {
        try {
            Utility.initDB();
            Class.forName("oracle.jdbc.driver.OracleDriver"); //3) initialize driver
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "12345678");
            Statement st = con.createStatement();
            String sql = "select sapid from outpass where vin in ('n','N')";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            int sap123;
            Scanner reader = new Scanner(System.in);
            System.out
                    .print("Enter the Sapid whose Verification you want to perform (Press 1 to return to homepage): ");
            sap123 = reader.nextInt();
            String sql1 = "update outpass set vin = 'y' where sapid = '" + sap123 + "'";
            st.executeUpdate(sql1);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

