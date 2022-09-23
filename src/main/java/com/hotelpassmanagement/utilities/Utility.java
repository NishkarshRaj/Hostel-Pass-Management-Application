package com.hotelpassmanagement.utilities;

public class Utility {
	//set classpath=C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6.jar;.
	 public static void initDB() {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	 
	 
	//To clear the screen!! Note: Only works in CMD not in Eclipse not in Bash
	 public static void cls() {
	        try {
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        } catch (Exception E) {
	            System.out.println(E);
	        }
	    }
}
