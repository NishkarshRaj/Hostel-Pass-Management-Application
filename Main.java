import java.util.Scanner;
import java.io.IOException;

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
System.out.println("Login Menu!!! Create Login Classes for Student, Warden, Guard");
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
System.out.println("\n\n\n\n\n\t\t\t\t\t\t\t\tPress Any key to continue:");
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