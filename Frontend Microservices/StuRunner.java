import java.util.Scanner;
import java.util.InputMismatchException;
class Student
{
String name;
String Course;
String Room;
int sapid;
String regnum;
long contactnumber;
String email;
public void SetStudent()
{
try
{
Scanner r2 = new Scanner(System.in);
System.out.print("Enter the name of the Student: ");
name = r2.nextLine();
System.out.print("Enter the Course name: ");
Course = r2.nextLine();
System.out.print("Enter your Room Number: ");
Room = r2.nextLine();
System.out.print("Enter the Registration Number of the Student: ");
regnum = r2.nextLine();
System.out.print("Enter the Email ID of the student: ");
email = r2.nextLine();
System.out.print("Enter SAP ID of the Student: ");
sapid = r2.nextInt();
System.out.print("Enter the contact number of the student: ");
contactnumber = r2.nextLong();
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
System.out.println("Course: " + Course);
System.out.println("Room Number: " + Room);
System.out.println("Registration Number: " + regnum);
System.out.println("Contact: " + contactnumber);
System.out.println("Email ID: " + email);
}
}

class StuRunner
{
public static void main(String args[])
{
Student stu = new Student();
stu.SetStudent();
stu.show();
}
}