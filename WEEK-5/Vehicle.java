class Vehicle 
{
    int speed;
    int fuelCapacity;

    void start() 
	{
        System.out.println("Vehicle started...");
    }
}
class Car extends Vehicle 
{
    void airConditioning() 
	{
        System.out.println("Car AC turned on.");
    }
}

class Bike extends Vehicle 
{
    void kickStart() {
        System.out.println("Bike kick-started.");
    }
}

class Truck extends Vehicle 
{
    int loadCapacity;

    void loadGoods(int weight) 
	{
        loadCapacity = weight;
        System.out.println("Truck loaded with " + loadCapacity + " kg of goods.");
    }
}

public class VehicleRentalSystem 
{
    public static void main(String[] args) 
	{
        Car car = new Car();
        car.start();
        car.airConditioning();

        Bike bike = new Bike();
        bike.start();
        bike.kickStart();

        Truck truck = new Truck();
        truck.start();
        truck.loadGoods(5000);
    }
}
