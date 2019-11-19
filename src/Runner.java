import java.util.Scanner;
import java.util.InputMismatchException;



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
//Clear c = new Clear();
//c.cls();
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
//c.cls();
createpass(name1,room1,course1,contact1,contactp1);
break;
default: 
//c.cls();
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
//Clear c1 = new Clear();
//c1.cls();
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
Outtime = rout.nextLine();
System.out.println("The Out Time has been specified!!!");
//Submit to the the Database;
}
public void verification_in() //used by guard
{
Scanner rin = new Scanner(System.in);
Intime = rin.nextLine();
System.out.println("The Intime has been specified!!!");
//Submit to the the Database;
}
}




class Runner
{
public static void main(String args[])
{
OutPass obj = new OutPass();
obj.createpass("Nishkarsh","D-203","B-Tech: Computer Science",9005445752l,9415956617l);
obj.permission();
}
}