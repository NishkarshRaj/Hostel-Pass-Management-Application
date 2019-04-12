import java.util.Scanner;

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
//System.out.println("Enter 1: Confirm Details and Submit OutPass Request");
//System.out.println("Enter 2: Discard and Recreate your Pass");
//System.out.print("Enter your choice: ");
//ch = reader.nextInt();
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
public void NotifyParent() //called by createpass()
{
}
public void NotifyWarden()  //called by createpass()
{
}
public void permission() //used by warden
{
}
public void verification_out() //used by guard
{
}
public void verification_in() //used by guard
{
}
}




class Runner
{
public static void main(String args[])
{
OutPass obj = new OutPass();
obj.createpass("Nishkarsh","D-203","B-Tech: Computer Science",9005445752l,9415956617l);
}
}