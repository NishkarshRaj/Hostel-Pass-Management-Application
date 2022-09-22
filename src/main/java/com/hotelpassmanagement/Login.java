package com.hotelpassmanagement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hotelpassmanagement.model.Guard;
import com.hotelpassmanagement.model.Student;
import com.hotelpassmanagement.model.Warden;
import com.hotelpassmanagement.utilities.Utility;

public class Login {
    public static void OptionMenu() {
        Scanner reader = new Scanner(System.in);
        Utility.cls();
        int ch;
        System.out.println("Login Page");
        System.out.println("1) Student Login");
        System.out.println("2) Warden Login");
        System.out.println("3) Guard Login");
        System.out.print("Enter your choice: ");
        try {
            ch = reader.nextInt();
            switch (ch) {
                case 1:
                    Utility.cls();
                    Student stu = new Student();
                    stu.loginpage();
                    break;
                case 2:
                    Utility.cls();
                    Warden w = new Warden();
                    w.loginpage();
                    break;
                case 3:
                    Utility.cls();
                    Guard g = new Guard();
                    g.loginpage();
                    break;
                default:
                    System.out.println("Wrong Choice Entered!!! Sending to the Login Menu again");
                    Utility.cls();
                    OptionMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println(e);
            OptionMenu();
        }
    }
    
    public void loginpage() {
      Scanner reader = new Scanner(System.in);
//String defaultpass = "Nishkarsh@123"; //until DB connected
      String n, p; //create check for username and password
      int elsech;
      System.out.println("Student Login Page!!");
      System.out.print("Enter Username: ");
      n = reader.nextLine();
      System.out.print("Enter Password: ");
      p = reader.nextLine();
      if (p.equals(
              "Nishkarsh@123")) //BCD!!! In Java .equals compares value while == is used to compare reference and
      // is only meant for objects
      {
          System.out.println("Access Granted!!");
      } else {
          System.out.println("Invalid Password!");
          System.out.println("Enter 1 to redirect to login page!");
          System.out.println("Enter 2 to exit the application!!");
          System.out.print("Enter your choice: ");
          elsech = reader.nextInt();
          if (elsech == 1) {
              loginpage();
          } else if (elsech == 2) {
              System.exit(0);
          } else {
              System.out.println("Wrong Choice Entered!!! Exiting the application!");
          }
      }
  }
}
