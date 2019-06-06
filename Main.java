import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.sql.*;
//Set path using
//set classpath=C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar;.
class DB
{
public void initDB()
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
}
catch(Exception e)
{
System.out.println(e);
}
}
}


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
String address; //Where you are going
String sap;
//creating a constructor
OutPass()
{
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
public void createpass(String name1,String room1,String course1, String sap1, long contact1, long contactp1) //used by student
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
switch(ch)
{
case 1:
DB obj = new DB();
obj.initDB();
try 
{
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678"); 
Statement st = con.createStatement(); 
String no = "n";
String sql = "insert into outpass values('"+sap1+"','"+name1+"','"+course1+"',"+contact1+","+contactp1+",'"+room+"','"+leavedate+"','"+returndate+"','"+no.charAt(0)+"','"+no.charAt(0)+"','"+no.charAt(0)+"')";
//System.out.println(sql);
st.executeUpdate(sql); 
con.close(); 
}
catch(Exception e)
{
System.out.println(e);
}
NotifyParent(contactp1);
NotifyWarden();
//Main m = new Main();
//m.homepage();
break;
case 2: 
c.cls();
createpass(name1,room1,course1,sap1,contact1,contactp1);
break;
case 3:
c.cls();
System.exit(0);
default: 
c.cls();
System.out.println("Wrong Choice Entered!!! Redirected to the Create Pass Menu\n\n");
createpass(name1,room1,course1,sap1,contact1,contactp1);
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
Clear c = new Clear();
//c.cls();
try
{
int sap123;
String ch123;
Scanner redder = new Scanner(System.in);
DB obj = new DB();
obj.initDB();
try
{
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678"); 
Statement st = con.createStatement(); 
String sql = "select * from outpass where permission in ('n','N')";
//System.out.println("Check 1");
ResultSet rs = st.executeQuery(sql);
//System.out.println("Check 2"); 
while(rs.next())
{
//System.out.println("Check 3");
//System.out.println(rs.getString(1) + rs.getString(2) +rs.getString(3) +rs.getString(4) +rs.getString(5) +rs.getString(6) +rs.getString(7) +rs.getString(8) +rs.getString(9) +rs.getString(10) +rs.getString(11));
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
if (ch123.equals("D"))
{
String sql1 = "update outpass set permission = 'd' where sapid ='" + sap123 + "'";
st.executeUpdate(sql1);
}
else if (ch123.equals("G"))
{
String sql1 = "update outpass set permission = 'g' where sapid ='" + sap123 + "'";
st.executeUpdate(sql1);
}
else
{
permission();
}
con.close(); 
}
catch(Exception e)
{
System.out.println(e);
permission();
}
}
catch(InputMismatchException e)
{
System.out.println(e);
permission();
}
}
public void NotifyStudent(int sapid)
{
Clear cd = new Clear();
cd.cls();
try 
{
DB object1 = new DB();
object1.initDB();
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678"); 
Statement st = con.createStatement(); 
String sql = "select permission from outpass where sapid = "+sapid+"";
ResultSet rs = st.executeQuery(sql); 
while(rs.next())
{
System.out.println(rs.getInt(1));
}
con.close(); 
}
catch(Exception e)
{
System.out.println(e);
}
}
public void verification_out() //used by guard
{
try
{
DB obj = new DB();
obj.initDB();
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678");
Statement st = con.createStatement(); 
String sql = "select sapid from outpass where vout in ('n','N')";
ResultSet rs = st.executeQuery(sql);
while(rs.next())
{
System.out.println(rs.getString(1));
}
int sap123;
Scanner reader = new Scanner(System.in);
System.out.print("Enter the Sapid whose Verification you want to perform (Press 1 to return to homepage): ");
sap123 = reader.nextInt();
String sql1 = "update outpass set vout = 'y' where sapid = '" + sap123 +"'";
st.executeUpdate(sql1);
con.close(); 
}
catch(Exception e)
{
System.out.println(e);
}
}
public void verification_in() 
{
try
{
DB obj = new DB();
obj.initDB();
Class.forName("oracle.jdbc.driver.OracleDriver"); //3) initialize driver
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678");
Statement st = con.createStatement(); 
String sql = "select sapid from outpass where vin in ('n','N')";
ResultSet rs = st.executeQuery(sql);
while(rs.next())
{
System.out.println(rs.getString(1));
}
int sap123;
Scanner reader = new Scanner(System.in);
System.out.print("Enter the Sapid whose Verification you want to perform (Press 1 to return to homepage): ");
sap123 = reader.nextInt();
String sql1 = "update outpass set vin = 'y' where sapid = '" + sap123 +"'";
st.executeUpdate(sql1);
con.close(); 
}
catch(Exception e)
{
System.out.println(e);
}
}
}


class IFStu
{
	public void menu(String n,String p)
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
		System.out.println("4) Check Outpass details");
		System.out.println("5) Logout");
		System.out.print("Enter your choice: ");
		choice123 = reader123.nextInt();
int s;
OutPass object11 = new OutPass();
DB obj11 = new DB();
Class.forName("oracle.jdbc.driver.OracleDriver"); 	
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678"); 
Statement st = con.createStatement();
ResultSet rs;
		switch(choice123)
		{
		case 1: c1.cls();
String sql5 = "select name,sapid,course from student where email = '"+n+"'";
st.executeQuery(sql5);
 rs = st.executeQuery(sql5); 
while(rs.next())
{
System.out.println("Name: " + rs.getString(1));
System.out.println("SAP ID: " + rs.getString(2));
System.out.println("Course: " + rs.getString(3));
//System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getInt(6)+rs.getInt(7)+rs.getString(8)+rs.getString(9));
}
//reader123.nextLine();
//menu(n,p);

try
{
int ch5;
System.out.println("\n\n\n");
System.out.println("Enter 1) Return to the homepage");
System.out.println("Enter 2) Logout and Exit Application");
System.out.println("Enter anything else to exit application");
System.out.print("Enter your choice: ");
ch5 = reader123.nextInt();
switch(ch5)
{
case 1:
menu(n,p);
break;
case 2:
c1.cls();
System.exit(0);
break;
default: 
}
}
catch(InputMismatchException e)
{
System.out.println(e);
menu(n,p);
}

		break;
		case 2:c1.cls();
System.out.println("Check Attendance Module to be added later");
menu(n,p);
		break;
		case 3: //c1.cls();
//System.out.println(n);
sql5 = "select name,roomnum,course,sapid,contact,contactp from student where email = '"+n+"'";
//sql5 = "select name from student where email = '"+n+"'";
//System.out.println(sql5);
ResultSet rs1 = st.executeQuery(sql5);
String s1,s2,s3,s4;
long i1,i2;
while(rs1.next())
{ 
s1 = rs1.getString(1);
s2 = rs1.getString(2);
s3 = rs1.getString(3);
s4 = rs1.getString(4);
i1 = rs1.getLong(5);
i2 = rs1.getLong(6);
object11.createpass(s1,s2,s3,s4,i1,i2);
}
try
{
int ch5;
System.out.println("\n\n\n");
System.out.println("Enter 1) Return to the homepage");
System.out.println("Enter 2) Logout and Exit Application");
System.out.println("Enter anything else to exit application");
System.out.print("Enter your choice: ");
ch5 = reader123.nextInt();
switch(ch5)
{
case 1:
menu(n,p);
break;
case 2:
c1.cls();
System.exit(0);
break;
default: 
}
}
catch(InputMismatchException e)
{
System.out.println(e);
menu(n,p);
}

//object11.createpass(s1,s2,s3,i4,i5);
//menu(n,p);
		break;
		case 4: c1.cls();
System.out.print("Re-enter your SAP Id for verification: ");
s = reader123.nextInt();
String sql = "select permission from outpass where sapid ="+s+"";
st.executeQuery(sql);
rs = st.executeQuery(sql); 
while(rs.next())
{
System.out.println(rs.getInt(1));
}
object11.NotifyStudent(s);
menu(n,p);
		break;
		case 5: c1.cls();
System.exit(0);
		break;
		default: c1.cls();
		System.out.println("Invalid Options Entered!!! Redirected to Main Menu");
		menu(n,p);
		}
con.close();
	}
	catch(InputMismatchException e)
	{
	System.out.println(e);
	menu(n,p);
	}
catch(Exception e)
{
System.out.println(e);
menu(n,p);
}
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
Clear c = new Clear();
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
System.out.print("Enter your password: ");
password = r2.nextLine();
System.out.print("Enter SAP ID of the Student: ");
sapid = r2.nextInt();
System.out.print("Enter the contact number of the guardian: ");
guardiancontact = r2.nextLong();
System.out.print("Enter the contact number of the student: ");
contactnumber = r2.nextLong(); //phone numbers of 10 digits cannot be incorporated inside int variable
}
catch(InputMismatchException e)
{
c.cls();
System.out.println(e);
SetStudent();
}
catch(Exception e)
{
c.cls();
System.out.println(e);
SetStudent();
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
public String insertionstatement()
{
//insert into student values ('name','sapid','password','regnum','Course',contactnumber,guardiancontact,'email','Room');
String inst = "insert into student values ('" + name + "','" + sapid + "','" + password + "','" + regnum + "','" + Course + "'," + contactnumber + "," +  guardiancontact + ",'" + email + "','" + Room + "')";
return inst;
}
public void loginpage()
{
Clear cl = new Clear();
Scanner reader = new Scanner(System.in);
//String defaultpass = "Nishkarsh@123"; //until DB connected
String n,p; //create check for username and password
int elsech;
System.out.println("Student Login Page!!");
System.out.print("Enter Email ID: ");
n = reader.nextLine();
System.out.print("Enter Password: ");
p = reader.nextLine();


//add DB here
int flag = 0;
try
{
DB odb = new DB();
odb.initDB();
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678"); 
Statement st = con.createStatement(); 
String sql = "select email,password from student where email = '" + n + "'";
//System.out.println(sql);
ResultSet rs = st.executeQuery(sql); 
while(rs.next())
{
flag = 1;
if(p.equals(rs.getString(2)))
{
flag = 2;
}
}
con.close();
}
catch(Exception e)
{
System.out.println(e);
}

//if (p.equals("Nishkarsh@123")) //BCD!!! In Java .equals compares value while == is used to compare reference and is only meant for objects 
if(flag == 2)
{
cl.cls();
IFStu obj12 = new IFStu();
obj12.menu(n,p);
}
else if(flag == 1)
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
else
{
System.out.println("Invalid Email!");
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
// Adding to the Database!!
// insert into student values ('name','sap','pass','regnum','course',c1,c2,'email','room');
try
{
DB obdb = new DB();
obdb.initDB();
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678");
Statement st = con.createStatement();
//String inst = "insert into student values ();"
String inst;
inst = s1.insertionstatement();
//System.out.println(inst);
st.executeUpdate(inst);
con.close();
}
catch(Exception e)
{
System.out.println(e);
}
s1.loginpage();
break;
case 2: 
c.cls();
createstu();
break;
case 3:
c.cls();
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
public void menu(String n,String p)
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
permissions(n,p);
break;
case 2:
c41.cls();
attendance(n,p);
break;
case 3:
c41.cls();
logout();
break;
default: 
c41.cls();
System.out.println("Wrong Choice Entered!! Redirected to the main menu!!!");
menu(n,p);
}
}
catch(InputMismatchException e)
{
System.out.println(e);
}
}
public void permissions(String n,String p)
{
c41.cls();
OutPass obj = new OutPass();
obj.permission();
menu(n,p);
}
public void attendance(String n,String p)
{
c41.cls();
System.out.println("Checking the attendance from DB");
menu(n,p);
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
System.out.print("Enter your password: ");
password = r2.nextLine();
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
public String insertionstatement()
{
String inst = "insert into warden values ('"+name+"',"+contactnumber+",'"+email+"','"+password+"')";
return inst;
}
public void loginpage()
{
Clear cl = new Clear();
Scanner reader = new Scanner(System.in);
String n,p,adminpass; //create check for username and password
int elsech;
System.out.println("Warden Login Page!!");
System.out.print("Enter Email ID: ");
n = reader.nextLine();
System.out.print("Enter Password: ");
p = reader.nextLine();
System.out.print("Enter the Administrator Password: ");
adminpass = reader.nextLine();

int flag = 0;
try
{
DB odb = new DB();
odb.initDB();
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678"); 
Statement st = con.createStatement(); 
String sql = "select email,password from warden where email = '" + n + "'";
//System.out.println(sql);
ResultSet rs = st.executeQuery(sql); 
while(rs.next())
{
flag = 1;
if(p.equals(rs.getString(2)))
{
flag = 2;
}
}
con.close();
}
catch(Exception e)
{
System.out.println(e);
}


if (flag == 2) 
{
	if(adminpass.equals("Nishkarsh@123"))
	{
	IFWarden obj = new IFWarden();
	obj.menu(n,p);
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
else if (flag == 1)
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
else
{
System.out.println("Invalid Email!");
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
System.out.println("Enter 1: Confirm Details and Create Warden!"); //Send to the Database!!! and then directly login!!!
System.out.println("Enter 2: Discard and redirect to Create Warden Page: ");
System.out.println("Enter 3: Exit the application: ");
System.out.print("Enter your choice: ");
ch = reader1.nextInt();
switch(ch)
{
case 1:
c.cls();
try
{
DB obdb = new DB();
obdb.initDB();
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678");
Statement st = con.createStatement();
String inst;
inst = w1.insertionstatement();
//System.out.println(inst);
st.executeUpdate(inst);
con.close();
}
catch(Exception e)
{
System.out.println(e);
}
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
public void menu(String n,String p)
{
c.cls();
System.out.println("1) Verification of Outing");
System.out.println("2) Verification of Return");
System.out.println("3) Logout");
System.out.print("Enter your choice: ");
ch = reader.nextInt();
switch(ch)
{
case 1: 
outdb(n,p);
try
{
int ch5;
System.out.println("\n\n\n");
System.out.println("Enter 1) Return to the homepage");
System.out.println("Enter 2) Logout and Exit Application");
System.out.println("Enter anything else to exit application");
System.out.print("Enter your choice: ");
ch5 = reader.nextInt();
switch(ch5)
{
case 1:
menu(n,p);
break;
case 2:
c.cls();
System.exit(0);
break;
default: 
}
}
catch(InputMismatchException e)
{
System.out.println(e);
menu(n,p);
}
//menu(n,p);
break;
case 2:
indb(n,p);
try
{
int ch5;
System.out.println("\n\n\n");
System.out.println("Enter 1) Return to the homepage");
System.out.println("Enter 2) Logout and Exit Application");
System.out.println("Enter anything else to exit application");
System.out.print("Enter your choice: ");
ch5 = reader.nextInt();
switch(ch5)
{
case 1:
menu(n,p);
break;
case 2:
c.cls();
System.exit(0);
break;
default: 
}
}
catch(InputMismatchException e)
{
System.out.println(e);
menu(n,p);
}

//menu(n,p);
break;
case 3:
c.cls();
System.exit(0);
break;
default: c.cls();
System.out.println("Wrong Choice Entered!!!");
menu(n,p);
}
}
public void outdb(String n,String p)
{
//c.cls();
obj.verification_out();
//menu(n,p);
}
public void indb(String n,String p)
{
//c.cls();
obj.verification_in();
//menu(n,p);
}
public void logout()
{
c.cls();
System.exit(0);
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
System.out.print("Enter password: ");
password = r2.nextLine();
System.out.print("Enter the Guard ID of the Guard: ");
guardID = r2.nextInt();
System.out.print("Enter the contact number of the Guard: ");
contactnumber = r2.nextLong(); //phone numbers of 10 digits cannot be incorporated inside int variable
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
public String insertionstatement()
{
//insert into guard ('name',contactnumber,'guardID','password');
String inst = "insert into guard values ('"+name+"',"+contactnumber+",'"+guardID+"','"+password+"')"; 
return inst;
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
System.out.print("Enter Guard ID: ");
n = reader.nextLine();
System.out.print("Enter Password: ");
p = reader.nextLine();
System.out.print("Enter the Administrator Password: ");
adminpass = reader.nextLine();
int flag = 0;
try
{
DB odb = new DB();
odb.initDB();
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678"); 
Statement st = con.createStatement(); 
String sql = "select guardid,password from guard where guardid = '" + n+"'";
ResultSet rs = st.executeQuery(sql); 
while(rs.next())
{
flag = 1;
if(p.equals(rs.getString(2)))
{
flag = 2;
}
}
con.close();
}
catch(Exception e)
{
System.out.println(e);
}
if (flag == 2)
	{
	if(adminpass.equals("Nishkarsh@123"))
	{
	IFGuard obj = new IFGuard();
	obj.menu(n,p);
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
else if(flag == 1)
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

else
{
System.out.println("Invalid Guard ID!");
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
//insert into guard ('name',contact,'gid','pass');
try
{
DB obdb = new DB();
obdb.initDB();
Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345678");
Statement st = con.createStatement();
//String inst = "insert into student values ();"
String inst;
inst = g1.insertionstatement();
//System.out.println(inst);
st.executeUpdate(inst);
con.close();
}
catch(Exception e)
{
System.out.println(e);
}
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


public static void mainfunction()
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
mainfunction();
}
catch(Exception e)
{
System.out.println(e);
mainfunction();
}
}

public static void main(String args[])
{
mainfunction();
}
}