import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;

//To clear the screen!! Note: Only works in CMD not in Eclipse not in Bash
class Clear
{
public static void cls()
{
try
{
new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
}
catch(Exception E)
{
System.out.println(E);
}
}
}

//Using Threads for sleep functionality
class Thread1 extends Thread
{
public void run()
{
try
{
Thread.sleep(5000);
}
catch(InterruptedException e)
{
System.out.println(e);
}
}
}

class Student
{
String name;
int sapid;
String regnum;
long contactnumber;
String email;
String password;
public void SetStudent()
{
try
{
Scanner r2 = new Scanner(System.in);
System.out.print("Enter the name of the Student: ");
name = r2.nextLine();
System.out.print("Enter the Registration Number of the Student: ");
regnum = r2.nextLine();
System.out.print("Enter the Email ID of the student: ");
email = r2.nextLine();
System.out.print("Enter SAP ID of the Student: ");
sapid = r2.nextInt();
System.out.print("Enter the contact number of the student: ");
contactnumber = r2.nextLong(); //phone numbers of 10 digits cannot be incorporated inside int variable
password = "Nishkarsh@123";
}
catch(InputMismatchException e)
{
System.out.println(e);
}
}
public void show()
{
System.out.println("Student Details are following: ");
System.out.println("Name: " + name);
System.out.println("SAP ID: " + sapid);
System.out.println("Registration Number: " + regnum);
System.out.println("Contact: " + contactnumber);
System.out.println("Email ID: " + email);
}
public void loginpage()
{
Clear cl = new Clear();
Scanner reader = new Scanner(System.in);
//String defaultpass = "Nishkarsh@123"; //until DB connected
String n,p; //create check for username and password
int elsech;
System.out.println("Student Login Page!!");
System.out.print("Enter Username: ");
n = reader.nextLine();
System.out.print("Enter Password: ");
p = reader.nextLine();
if (p.equals("Nishkarsh@123")) //BCD!!! In Java .equals compares value while == is used to compare reference and is only meant for objects 
{
System.out.println("Access Granted!!");
}
else
{
System.out.println("Invalid Password!");
System.out.println("Enter 1 to redirect to login page!");
System.out.println("Enter 2 to exit the application!!");
System.out.print("Enter your choice: ");
elsech = reader.nextInt();
if(elsech == 1)
{
cl.cls();
loginpage();
}
else if(elsech == 2)
{
cl.cls();
System.exit(0);
}
else
{
cl.cls();
System.out.println("Wrong Choice Entered!!! Exiting the application!");
System.exit(0);
}
}
}
}


class Login
{
public static void OptionMenu()
{
Scanner reader = new Scanner(System.in);
Clear c = new Clear();
int ch;
System.out.println("Login Page");
System.out.println("1) Student Login");
System.out.println("2) Warden Login");
System.out.println("3) Guard Login");
System.out.print("Enter your choice: ");
try
{
ch = reader.nextInt();
switch(ch)
{
case 1:
c.cls();
Student stu = new Student();
stu.loginpage();
break;
case 2:
c.cls();
System.out.println("Warden Login");
break;
case 3:
c.cls();
System.out.println("Guard Login");
break;
default: System.out.println("Wrong Choice Entered!!! Sending to the Login Menu again");
c.cls();
OptionMenu();
}
}
catch(InputMismatchException e)
{
System.out.println(e);
}
}
}


class Main 
{
public static void homepage()
{
Clear c1 = new Clear();
int choiceoption1;
Scanner r = new Scanner(System.in);
System.out.println("Option Menu!");
System.out.println("1) Login");
System.out.println("2) Create Account");
System.out.println("3) Exit The Application");
System.out.print("Enter your choice: ");
choiceoption1 = r.nextInt();
c1.cls();
switch(choiceoption1)
{
case 1:
c1.cls();
Login l = new Login();
Login.OptionMenu();
break;
case 2:
System.out.println("Create Account for Student, Warden, Guard using DataBase connectivity!!!");
break;
case 3:
System.out.println("Thanks for Using this Application");
System.exit(0);
break;
default: System.out.println("Invalid option!!! Redirecting to the HomePage"); homepage();
}
}


public static void main(String args[])
{
//Declarations
Scanner reader = new Scanner(System.in);
Clear c = new Clear();
Thread1 t = new Thread1();
char startkey;
//Declarations end
try
{
c.cls();
System.out.println("\t\t\t\t\t\t\tWelcome to the Hostel Pass Management Application");
System.out.print("\n\n\n\n\n\t\t\t\t\t\t\t\tPress Any key to continue: ");
startkey = reader.nextLine().charAt(0);
c.cls();
homepage();
//c.cls();
}
catch(StringIndexOutOfBoundsException e)
{
System.out.println(e);
}
catch(Exception e)
{
System.out.println(e);
}
}
}