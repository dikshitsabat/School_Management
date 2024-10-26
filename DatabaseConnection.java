package SchoolPack1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnection {   // create a class for connect the database
	static String root;  // create variable for root password in database
	public static void buildDatabase() throws ClassNotFoundException, SQLException  {  // class.forname may throw class not found exception
		int x;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL Driver jar file  into java class path
		int flag = 1;   // declare a variable is 1, for root password
		Scanner sc=new Scanner(System.in);   // Scanner use for take input from user.
		System.out.println("Connecting To Database ⚗️⚗️⚗️⚗️⚗️");
		do {
			System.out.print("Enter Root Password : ");
			root = sc.nextLine();    // read input password and store into root variable (string type)
			try 
			{
				Connection check = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false","root","@Babuni1234"); // connect to database using root password 
				flag = 1;    // connected 
			}
			catch(Exception e) {
				flag = 0;   // Failed and try again 
				System.out.println("WRONG PASSWORD ENTERED 🚫! ENTER AGAIN !");
			}
		} while(flag==0);  // until correct password match the loop will be continue 
		
		PreparedStatement query; 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false","root","@Babuni1234");
		
		//query=con.prepareStatement("drop database SchoolManagement");
		//x=query.executeUpdate();
		
		query=con.prepareStatement("CREATE DATABASE IF NOT EXISTS SchoolManagement");
		x=query.executeUpdate();
		
		query= con.prepareStatement("use SchoolManagement");   // selecting database 
	    x=query.executeUpdate(); // selected database
		
	    // Login Details Table
	    // Teacher Details Table 
	    // Student Details Table
	    
	  //creating table Login Details
		query=con.prepareStatement("create table if not exists logindetails ( userID int not null, password varchar(20) not null, userType char not null, primary key(userID) )");
	    x=query.executeUpdate();
	    
	  //creating table Teacher Details
		query=con.prepareStatement("create table if not exists teacherdetails ( TeacherId int not null, Name varchar(20) not null, Age int not null, Email varchar(30) not null, ContactNumber varchar(30) not null, Address varchar(30) not null, primary key(TeacherID) )");
	    x=query.executeUpdate();
	    
	  //creating table Student Details
		query=con.prepareStatement("create table if not exists studentdetails ( StudentId int not null, Name varchar(20) not null, Age int not null, Email varchar(30) not null, ContactNumber varchar(30) not null, Address varchar(30) not null, primary key(StudentID) )");
	    x=query.executeUpdate();

		}
		catch (Exception e ) {
			e.printStackTrace();
		}
		System.out.println("DATABASE CONNECTED SUCCESSFULLY.....");
	}
	public static void main(String[]args) throws ClassNotFoundException, SQLException {
		buildDatabase();
		
	}
}