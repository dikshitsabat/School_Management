package SchoolPack1;

public class Student extends Classroom{
	private int studentID;
	private String password;
	Student(int x,String y)
	{
		studentID=x;
		password=y;
	}
public void StudentPage() {
	System.out.println("Welcome TO Student Page");
	}

}
