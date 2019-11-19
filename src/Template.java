import java.util.Scanner;
class Login
{
public void loginpage()
{
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
loginpage();
}
else if(elsech == 2)
{
System.exit(0);
}
else
{
System.out.println("Wrong Choice Entered!!! Exiting the application!");
}
}
}
}

class Template
{
public static void main(String args[])
{
Login l = new Login();
l.loginpage();
}
}