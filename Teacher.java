package SchoolPack1;

public class Teacher extends Classroom {
		private int teacherID;
		private String password;
		Teacher(int x,String y)
		{
			teacherID=x;
			password=y;
		}
	public void TeacherPage() {
		System.out.println("Welcome TO Teacher Page");
	}

}
