# Hostel-Pass-Management-Application


## Pre-Requisites

1. Java JDK 1.8 or above installed on local system
2. Oracle 11g Express Edition or above for Database Management

## How to execute

1. Clone this repository on your local machine
```
$ git clone "https://github.com/NishkarshRaj/Hostel-Pass-Management-Application.git"
```

2. Open SQL Plus on your local machine and configure Database by using the Creation and insertion files in **dbms_files** directory.

3. Visit **src** directory on your local machine via command line and type the following command:
```
$ cd {path to project}/src/
$ set classpath=C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar;.
```

4. Execute the Main.java file in src directory
```
$ javac Main.java
$ java Main
```

## Code of Conduct

[Code of Conduct](https://github.com/NishkarshRaj/Hostel-Pass-Management-Application/blob/master/CODE_OF_CONDUCT.md)

## How to Contribute

For contributing, check the guidelines [here](https://github.com/NishkarshRaj/Hostel-Pass-Management-Application/blob/master/CONTRIBUTING.md)

## Future Scope

1. Create a GUI version
2. Make an Attendance Management System and integrate it with this project
3. Use and online database for concurrency
