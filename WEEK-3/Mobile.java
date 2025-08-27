public class Mobile
 {
    String brand;
    String model;
    double price;

    Mobile(String brand, String model, double price) 
	{
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    void showDetails() 
	{
        System.out.println("Brand : " + brand);
        System.out.println("Model : " + model);
        System.out.println("Price : ₹" + price);
    }

    public static void main(String[] args) 
	{
        Mobile mobile1 = new Mobile("Apple", "iPhone 14", 79999);
        Mobile mobile2 = new Mobile("Samsung", "Galaxy S22", 69999);
        Mobile mobile3 = new Mobile("OnePlus", "Nord CE 3", 24999);

        mobile1.showDetails();
        mobile2.showDetails();
        mobile3.showDetails();
    }
}
