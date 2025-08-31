class Student 
{
    private int marks; 

    public int getMarks() 
	{
        return marks;
    }

    public void setMarks(int marks) 
	{
        this.marks = marks;
    }
}

class Teacher 
{
    public void updateMarks(Student s, int newMarks) 
	{
        s.setMarks(newMarks); 
    }
}

class StudentUser 
{
    public void viewMarks(Student s) 
	{
        System.out.println("Student Marks: " + s.getMarks());
    }
}

public class StudentMarksSystem 
{
    public static void main(String[] args) 
	{
        Student student = new Student();
        Teacher teacher = new Teacher();
        StudentUser stuUser = new StudentUser();

        teacher.updateMarks(student, 85);

        stuUser.viewMarks(student);
    }
}
