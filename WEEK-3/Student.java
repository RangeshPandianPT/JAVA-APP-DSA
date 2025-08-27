public class Student
 {

    int rollNo;
    String name;
    double marks;


    Student(int rollNo, String name, double marks)
	{
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }


    void display()
	{
        System.out.println("Roll No : " + rollNo);
        System.out.println("Name    : " + name);
        System.out.println("Marks   : " + marks);

    }


    public static void main(String[] args) 
	{
        Student student1 = new Student(101, "RANGESH", 89.5);
        Student student2 = new Student(102, "AAKASH", 92.0);

        student1.display();
        student2.display();
    }
}
