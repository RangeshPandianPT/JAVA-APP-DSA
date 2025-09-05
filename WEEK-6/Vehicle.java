// Week 6 - Tutorial Assignment
// Scenario 1: Vehicle Speed - Method Overriding

class Vehicle {
    void speed() {
        System.out.println("Vehicle speed is not defined.");
    }
}

class Car extends Vehicle {
    @Override
    void speed() {
        System.out.println("Car runs at 100 km/h.");
    }
}

class Bike extends Vehicle {
    @Override
    void speed() {
        System.out.println("Bike runs at 80 km/h.");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new Car();   
        Vehicle v2 = new Bike();  

        v1.speed();  
        v2.speed(); 
    }
}
