class Patient 
{
    String name;
    int age;

    public Patient(String name, int age) 
	{
        this.name = name;
        this.age = age;
    }

    public void displayDetails() 
	{
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class OutPatient extends Patient 
{
    public OutPatient(String name, int age) 
	{
        super(name, age);
    }

    @Override
    public void displayDetails() 
	{
        System.out.println("OutPatient Details:");
        super.displayDetails();
    }
}

class InPatient extends Patient 
{
    int roomNumber;

    public InPatient(String name, int age, int roomNumber) 
	{
        super(name, age);
        this.roomNumber = roomNumber;
    }

    @Override
    public void displayDetails() 
	{
        System.out.println("InPatient Details:");
        super.displayDetails();
        System.out.println("Room Number: " + roomNumber);
    }
}

public class HospitalSystem 
{
    public static void main(String[] args) 
	{
        OutPatient op = new OutPatient("Ramesh", 30);
        InPatient ip = new InPatient("Suresh", 45, 101);

        op.displayDetails();
        System.out.println();
        ip.displayDetails();
    }
}
