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

class OutPass
{
//Specifying Data Members
String name;
String room;
String course;
long contact;
long contactp;
String leavedate; //specified by student !!! Try to use Date and Time Utility
String returndate; //specified by student
String Outtime; //specified by guard on verification
String Intime; //specified by guard on verification
String address; //Where you are going
//creating a constructor
OutPass()
{
name = "";
room = "";
course = "";
contact = 0000;
contactp = 0000;
leavedate = "";
returndate = "";
Outtime = "";
Intime = "";
address = "";
}
public void createpass(String name1,String room1,String course1,long contact1, long contactp1) //used by student
{
try
{
int ch;
Clear c = new Clear();
c.cls();
System.out.println("Create Out Pass Module\n\n");
Scanner reader = new Scanner(System.in);
name = name1;
room = room1;
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
System.out.print("Enter your choice: ");
ch = reader.nextInt();
switch(ch)
{
case 1: System.out.println("OutPass Submitted"); //Add A Module to send it to the DB so that Warden and Guard can check and verify from DB
NotifyParent(contactp1);
NotifyWarden();
//Main m = new Main();
//m.homepage();
break;
case 2: 
c.cls();
createpass(name1,room1,course1,contact1,contactp1);
break;
default: 
c.cls();
System.out.println("Wrong Choice Entered!!! Redirected to the Create Pass Menu\n\n");
createpass(name1,room1,course1,contact1,contactp1);
}
}
catch(InputMismatchException e)
{
System.out.println(e);
}
}
public void show() //called by createpass()
{
Clear c1 = new Clear();
c1.cls();
System.out.println("Verify your details for the current OutPass\n");
System.out.println("Name: " + name);
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
try
{
int ch123;
Scanner redder = new Scanner(System.in);
//Check from DB
System.out.println("Enter 1 to grant permission or else enter anything to deny!");
ch123 = redder.nextInt();
NotifyStudent(ch123);
}
catch(InputMismatchException e)
{
System.out.println(e);
}
}
public void NotifyStudent(int flag)
{
if(flag == 1)
{
System.out.println("Permission Granted");
}
else
{
System.out.println("Permission Denied");
}
}
public void verification_out() //used by guard
{
Scanner rout = new Scanner(System.in);
System.out.print("Enter the Out time: ");
Outtime = rout.nextLine();
System.out.println("The Out Time has been specified!!!");
//Submit to the the Database;
}
public void verification_in() //used by guard
{
Scanner rin = new Scanner(System.in);
System.out.print("Enter the In time: ");
Intime = rin.nextLine();
System.out.println("The Intime has been specified!!!");
//Submit to the the Database;
}
}


class IFStu
{
	public void menu()
	{
	try
	{
		int choice123;
		Scanner reader123 = new Scanner(System.in);
		Clear c1 = new Clear();
		c1.cls();
		System.out.println("1) Check Your Details");
		System.out.println("2) Check Attendance");
		System.out.println("3) Create Outpass");
		System.out.println("4) Logout");
		System.out.print("Enter your choice: ");
		choice123 = reader123.nextInt();
		switch(choice123)
		{
		case 1: c1.cls();
 checkdetails();
		break;
		case 2:c1.cls();
 attendance();
		break;
		case 3: c1.cls();
 createout();
		break;
		case 4: c1.cls();
 logout();
		break;
		default: c1.cls();
		System.out.println("Invalid Options Entered!!! Redirected to Main Menu");
		menu();
		}
	}
	catch(InputMismatchException e)
	{
	System.out.println(e);
	menu();
	}
	}
public void checkdetails()
{
System.out.println("Checking Details from Database");
}
public void attendance()
{
System.out.println("Checking Attendance from Database");
}
public void createout()
{
OutPass obj = new OutPass();
obj.createpass("","","",123l,123l); //sending default parameters until DB connected
}
public void logout()
{
System.exit(0);
}
}



class Student
{
String name;
int sapid;
String Course;
String Room;
String regnum;
long contactnumber;
long guardiancontact;
String email;
String password;
public void SetStudent()
{
try
{
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
System.out.print("Enter SAP ID of the Student: ");
sapid = r2.nextInt();
System.out.print("Enter the contact number of the guardian: ");
guardiancontact = r2.nextLong();
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
System.out.println("Course: " + Course);
System.out.println("Room Number: " + Room);
System.out.println("SAP ID: " + sapid);
System.out.println("Registration Number: " + regnum);
System.out.println("Contact: " + contactnumber);
System.out.println("Guardian Contact number: " + guardiancontact);
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
cl.cls();
IFStu obj12 = new IFStu();
obj12.menu();
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


class CreateStudent extends Student
{
public void createstu()
{
Clear c = new Clear();
c.cls();
int ch;
Scanner reader1 = new Scanner(System.in);
System.out.println("Create a new Student Account!!!");
Student s1 = new Student();
s1.SetStudent();
c.cls();
s1.show();
System.out.println("\n\n\n");
System.out.println("Enter 1: Confirm Details and Create Student!"); //Send to the Database!!! and then directly login!!!
System.out.println("Enter 2: Discard and redirect to Create Student Page: ");
System.out.println("Enter 3: Exit the application: ");
System.out.print("Enter your choice: ");
ch = reader1.nextInt();
switch(ch)
{
case 1:
c.cls();
System.out.println("Add details to the Database!!! To be done");
System.out.println("Redirecting to the Login Page");
s1.loginpage();
break;
case 2: 
c.cls();
createstu();
break;
case 3:
System.exit(0);
break;
default: 
c.cls();
System.out.println("Wrong Choice Entered! Exiting Application");
System.exit(0);
}
}
}


class IFWarden
{
Clear c41 = new Clear();
Scanner reader = new Scanner(System.in);
int ch;
public void menu()
{
try
{
c41.cls();
System.out.println("Warden Home Page\n\n");
System.out.println("1) Check for new OutPass Requests");
System.out.println("2) Check Student Attendance");
System.out.println("3) Logout");
System.out.print("Enter your choice: ");
ch = reader.nextInt();
switch(ch)
{
case 1:
c41.cls();
permissions();
break;
case 2:
c41.cls();
attendance();
break;
case 3:
c41.cls();
logout();
break;
default: 
c41.cls();
System.out.println("Wrong Choice Entered!! Redirected to the main menu!!!");
menu();
}
}
catch(InputMismatchException e)
{
System.out.println(e);
}
}
public void permissions()
{
c41.cls();
OutPass obj = new OutPass();
obj.permission();
menu();
}
public void attendance()
{
c41.cls();
System.out.println("Checking the attendance from DB");
menu();
}
public void logout()
{
c41.cls();
System.exit(0);
}
}


class Warden
{
String name;
long contactnumber;
String email;
String password;
public void SetWarden()
{
try
{
Scanner r2 = new Scanner(System.in);
System.out.print("Enter the name of the Warden: ");
name = r2.nextLine();
System.out.print("Enter the Email ID of the Warden: ");
email = r2.nextLine();
System.out.print("Enter the contact number of the Warden: ");
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
System.out.println("Warden Details are following: ");
System.out.println("Name: " + name);
System.out.println("Contact: " + contactnumber);
System.out.println("Email ID: " + email);
}
public void loginpage()
{
Clear cl = new Clear();
Scanner reader = new Scanner(System.in);
//String defaultpass = "Nishkarsh@123"; //until DB connected
String n,p,adminpass; //create check for username and password
//adminpass = "Nishkarsh@123" //Extra security for admin interface
int elsech;
System.out.println("Warden Login Page!!");
System.out.print("Enter Username: ");
n = reader.nextLine();
System.out.print("Enter Password: ");
p = reader.nextLine();
System.out.print("Enter the Administrator Password: ");
adminpass = reader.nextLine();
if (p.equals("Nishkarsh@123")) //BCD!!! In Java .equals compares value while == is used to compare reference and is only meant for objects 
{
if(adminpass.equals("Nishkarsh@123"))
{
IFWarden obj = new IFWarden();
obj.menu();
}
else
{
System.out.println("Wrong Admin Password");
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


class CreateWarden extends Warden
{
public void createwarden()
{
Clear c = new Clear();
c.cls();
int ch;
Scanner reader1 = new Scanner(System.in);
System.out.println("Create a new Warden Account!!!");
Warden w1 = new Warden();
w1.SetWarden();
c.cls();
w1.show();
System.out.println("\n\n\n");
System.out.println("Enter 1: Confirm Details and Create Wardne!"); //Send to the Database!!! and then directly login!!!
System.out.println("Enter 2: Discard and redirect to Create Warden Page: ");
System.out.println("Enter 3: Exit the application: ");
System.out.print("Enter your choice: ");
ch = reader1.nextInt();
switch(ch)
{
case 1:
c.cls();
System.out.println("Add details to the Database!!! To be done");
System.out.println("Redirecting to the Login Page");
w1.loginpage();
break;
case 2: 
c.cls();
createwarden();
break;
case 3:
System.exit(0);
break;
default: 
c.cls();
System.out.println("Wrong Choice Entered! Exiting Application");
System.exit(0);
}
}
}


class IFGuard
{
OutPass obj = new OutPass();
Clear c = new Clear();
int ch;
Scanner reader = new Scanner(System.in);
public void menu()
{
c.cls();
System.out.println("1) Put Out Time in DB");
System.out.println("2) Put In Time in DB");
System.out.println("3) Logout");
System.out.print("Enter your choice: ");
ch = reader.nextInt();
switch(ch)
{
case 1: 
outdb();
menu();
break;
case 2:
indb();
menu();
break;
case 3:
logout();
break;
default: c.cls();
System.out.println("Wrong Choice Entered!!!");
menu();
}
}
public void outdb()
{
c.cls();
obj.verification_out();
menu();
}
public void indb()
{
c.cls();
obj.verification_in();
menu();
}
public void logout()
{
c.cls();
logout();
}
}




class Guard
{
String name;
long contactnumber;
int guardID;
String password;
public void SetGuard()
{
try
{
Scanner r2 = new Scanner(System.in);
System.out.print("Enter the name of the Guard: ");
name = r2.nextLine();
System.out.print("Enter the Guard ID of the Guard: ");
guardID = r2.nextInt();
System.out.print("Enter the contact number of the Guard: ");
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
System.out.println("Guard Details are following: ");
System.out.println("Name: " + name);
System.out.println("Contact: " + contactnumber);
System.out.println("GuardID: " + guardID);
}
public void loginpage()
{
Clear cl = new Clear();
Scanner reader = new Scanner(System.in);
//String defaultpass = "Nishkarsh@123"; //until DB connected
String n,p,adminpass; //create check for username and password
//adminpass = "12345678" //Extra security for admin interface but easy because guard may not remember difficult password
int elsech;
System.out.println("Guard Login Page!!");
System.out.print("Enter Username: ");
n = reader.nextLine();
System.out.print("Enter Password: ");
p = reader.nextLine();
System.out.print("Enter the Administrator Password: ");
adminpass = reader.nextLine();
if (p.equals("Nishkarsh@123")) //BCD!!! In Java .equals compares value while == is used to compare reference and is only meant for objects 
{
if(adminpass.equals("12345678"))
{
IFGuard obj = new IFGuard();
obj.menu();
}
else
{
System.out.println("Wrong Admin Password");
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


class CreateGuard extends Guard
{
public void createguard()
{
Clear c = new Clear();
c.cls();
int ch;
Scanner reader1 = new Scanner(System.in);
System.out.println("Create a new Guard Account!!!");
Guard g1 = new Guard();
g1.SetGuard();
c.cls();
g1.show();
System.out.println("\n\n\n");
System.out.println("Enter 1: Confirm Details and Create Guard!"); //Send to the Database!!! and then directly login!!!
System.out.println("Enter 2: Discard and redirect to Create Guard Page: ");
System.out.println("Enter 3: Exit the application: ");
System.out.print("Enter your choice: ");
ch = reader1.nextInt();
switch(ch)
{
case 1:
c.cls();
System.out.println("Add details to the Database!!! To be done");
System.out.println("Redirecting to the Login Page");
g1.loginpage();
break;
case 2: 
c.cls();
createguard();
break;
case 3:
System.exit(0);
break;
default: 
c.cls();
System.out.println("Wrong Choice Entered! Exiting Application");
System.exit(0);
}
}
}

class Login
{
public static void OptionMenu()
{
Scanner reader = new Scanner(System.in);
Clear c = new Clear();
c.cls();
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
Warden w = new Warden();
w.loginpage();
break;
case 3:
c.cls();
Guard g = new Guard();
g.loginpage();
break;
default: System.out.println("Wrong Choice Entered!!! Sending to the Login Menu again");
c.cls();
OptionMenu();
}
}
catch(InputMismatchException e)
{
System.out.println(e);
OptionMenu();
}
}
}


class Create
{
public void createaccounts()
{
try
{
Clear c5 = new Clear();
c5.cls();
Scanner red5 = new Scanner(System.in);
int ch5;
System.out.println("Create Account Page\n\n");
System.out.println("1) Create Student Account");
System.out.println("2) Create Warden Account");
System.out.println("3) Create Guard Account");
System.out.println("4) Exit Application");
System.out.print("Enter your choice: ");
ch5 = red5.nextInt();
switch(ch5)
{
case 1: System.out.println("Student Account");
CreateStudent cs = new CreateStudent();
cs.createstu();
break;
case 2: System.out.println("Warden Account");
CreateWarden cw = new CreateWarden();
cw.createwarden();
break;
case 3: System.out.println("Guard Account");
CreateGuard cg = new CreateGuard();
cg.createguard();
break;
case 4:
c5.cls();
System.exit(0);
default: 
c5.cls();
System.out.println("Wrong Choice!! Redirecting to the Create Account Page");
createaccounts();
}
}
catch(Exception e)
{
System.out.println(e);
createaccounts();
}
}
}



class Main 
{
public static void homepage()
{
try
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
Create c7 = new Create();
c7.createaccounts();
break;
case 3:
System.out.println("Thanks for Using this Application");
System.exit(0);
break;
default: System.out.println("Invalid option!!! Redirecting to the HomePage"); homepage();
}
}
catch(Exception e)
{
System.out.println(e);
homepage();
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