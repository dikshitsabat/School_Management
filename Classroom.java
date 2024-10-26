package SchoolPack1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Classroom {
	static int TID;  // create variable for Teacher id
	static int SID;  // create variable for Student id

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		DatabaseConnection.buildDatabase();
		// Register For Teacher
		// Register For Student
		// Login To System
		// Exit To System
		
		//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Scanner sc=new Scanner(System.in);   // Scanner use for take input from user.
		System.out.println("Welcome to School Management");
		
		int store; // create a variable to store value from do-while loop
		
		do {
			System.out.println("🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖");
			System.out.println("1 - Register For Teacher");
			System.out.println("2 - Register For Student");
			System.out.println("3 - Lonin To System");
			System.out.println("4 - Exist To System");
			System.out.println("🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖");
			System.out.print("Enter Choice : ");
			store = sc.nextInt();  // 
			
			if(store==1)
				registerTeacher(); // if ch is 1 then call method registeradmin
			else if(store==2)
				registerStudent();   // if ch is 2 then call method registercustomer
			else if(store==3)
				loginSystem();     // if ch is 3 then call method loginsystem
			else if(store==4)
				System.out.println("Exiting the system. Goodbye!");
			else if(store>=5)
				System.out.println("Wrong choice");  // if user select after 4 integer than print wrong choice and print printMessage.
		}while(store!=4); // Exist the loop if user select no 4 option.
		
	}
	static void loginSystem(){
		String hold;
		Scanner sc=new Scanner(System.in);
		System.out.println("WELCOME TO LOGIN PAGE");
		System.out.println("🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖");
		
		//selecting data from login info table
		ArrayList<Integer> id=new ArrayList<Integer>();
		ArrayList<String> password=new ArrayList<String>();
		ArrayList<Character> type=new ArrayList<Character>();
		
		try {
			int uid;
			String passw;
			char tp=' ';
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			
			PreparedStatement ps=con.prepareStatement("select * from logindetails"); // sql query to select login table
			ResultSet rs=ps.executeQuery();   // execute query 
			while(rs.next())
			{
				id.add(Integer.parseInt(rs.getString("userID")));  // adding user id 
				password.add(rs.getString("password")); // add password
				type.add((rs.getString("userType")).charAt(0));
			}
			
			int flag1=0,flag2=0;
			int f1,f2;
			do {
				System.out.print("Enter USER ID : ");
				uid=sc.nextInt();
				sc.nextLine();
				System.out.print("Enter PASSWORD : ");
				passw=sc.nextLine();
				
				f1=id.indexOf(uid); // f1 hold the indexof uid value, indexof is arraylist method here indexof search uid place
				f2=password.indexOf(passw);
				
				if(f1==f2 && (f1!=-1 && f2!=-1)) // f1 f2 is equal then uid and passw is equal place and  (f1!=-1 && f2!=-1) both are not equal means uid and pass is perfect place user is true, if both condition is true means user is same 
				{
					flag1=1;  // if the condition is true return 1
					flag2=1;  // if the condition is true return 1
				}
				
				if(flag1==0 || flag2==0)
				{
					System.out.println("INVALID CREDENTIALS , ENTER AGAIN !");
					System.out.print("Do you want to continue ( Y for yes, N for No)");
					hold=sc.nextLine();
					if(hold.equalsIgnoreCase("N"))
						break;
				}
				
			}while(flag1==0 || flag2==0);
			
			if(flag1==1 && flag2==1)
			{
				tp=type.get(id.indexOf(uid));
			}
			if(tp=='T')
			{
				Teacher ob=new Teacher(uid,passw);
				ob.TeacherPage();
			}
			else if(tp=='S')
			{
				Student ob=new Student(uid,passw);
				ob.StudentPage();
			}
			
			}
		catch(Exception e) {
			e.printStackTrace();
		}

	} 
	
	static void registerTeacher() throws IOException {
		Scanner sc=new Scanner(System.in);
		String password,name,contactnumber,address,email;
		int age;
		System.out.println("WELCOME TO Teacher REGISTRATION PAGE");
		System.out.println("🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖");
		setTID();
		System.out.println("Teacher ID = "+TID);
		System.out.print("Enter password = ");
		password=sc.nextLine();
		System.out.print("Enter Name = ");
		name=sc.nextLine();
		System.out.print("Enter age = ");
		age=sc.nextInt();
		sc.nextLine();
		System.out.print("Enter contact number = ");
		contactnumber=sc.nextLine();
		System.out.print("Enter address = ");
		address=sc.nextLine();
		System.out.print("Enter email = ");
		email=sc.nextLine();
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
		PreparedStatement ps=con.prepareStatement("insert into teacherdetails(TeacherID,Name,Age,Email,Address,ContactNumber) values(?,?,?,?,?,?)");
		PreparedStatement ps1=con.prepareStatement("insert into logindetails(userID,password,userType) values(?,?,?)");
		ps.setString(1, Integer.toString(TID));
		ps.setString(2, name);
		ps.setString(3,Integer.toString(age));
		ps.setString(4, email);
		ps.setString(5, address);
		ps.setString(6, contactnumber);
		ps1.setString(1, Integer.toString(TID));
		ps1.setString(2,password);
		ps1.setString(3, Character.toString('T'));
		int x=ps.executeUpdate();
		int y=ps1.executeUpdate();
		if(x>0 && y>0)
			System.out.println("Teacher REGISTRATION DONE SUCCESSFULLY !");
		else
			System.out.println("REGISTRATION FAILED !");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	static void registerStudent() throws IOException {
		Scanner sc=new Scanner(System.in);
		String password,name,contactnumber,address,email;
		int age;
		System.out.println("WELCOME TO Teacher REGISTRATION PAGE");
		System.out.println("🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖🤖");
		setSID();
		System.out.println("Student ID = "+SID);
		System.out.print("Enter password = ");
		password=sc.nextLine();
		System.out.print("Enter Name = ");
		name=sc.nextLine();
		System.out.print("Enter age = ");
		age=sc.nextInt();
		sc.nextLine();
		System.out.print("Enter contact number = ");
		contactnumber=sc.nextLine();
		System.out.print("Enter address = ");
		address=sc.nextLine();
		System.out.print("Enter email = ");
		email=sc.nextLine();
		
		//inserting data into database
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
				PreparedStatement ps=con.prepareStatement("insert into studentdetails(StudentID,Name,Age,Email,Address,ContactNumber) values(?,?,?,?,?,?)");
				PreparedStatement ps1=con.prepareStatement("insert into logindetails(userID,password,userType) values(?,?,?)");
				ps.setString(1, Integer.toString(SID));
				ps.setString(2, name);
				ps.setString(3,Integer.toString(age));
				ps.setString(4, email);
				ps.setString(5, address);
				ps.setString(6, contactnumber);
				ps1.setString(1, Integer.toString(SID));
				ps1.setString(2,password);
				ps1.setString(3, Character.toString('S'));
				int x=ps.executeUpdate();
				int y=ps1.executeUpdate();
				if(x>0 && y>0)
					System.out.println("REGISTRATION DONE SUCCESSFULLY !");
				else
					System.out.println("REGISTRATION FAILED !");
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
		
	}
	
	static void setSID()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			PreparedStatement ps=con.prepareStatement("select StudentID from studentdetails");
			ResultSet rs=ps.executeQuery();
			int x=199;
			while(rs.next()) {
				x=Integer.parseInt(rs.getString("StudentID"));
			}
			SID=x+1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println(e);
		}
	}
	
	static void setTID()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement?autoReconnect=true&useSSL=false","root",DatabaseConnection.root);
			PreparedStatement ps=con.prepareStatement("select TeacherID from teacherdetails");
			ResultSet rs=ps.executeQuery();
			int x=99;
			while(rs.next()) {
				x=Integer.parseInt(rs.getString("TeacherID"));
			}
			TID=x+1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println(e);
		}
	}
}