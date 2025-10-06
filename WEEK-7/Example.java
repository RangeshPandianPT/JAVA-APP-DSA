
class Calculator {
    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method to add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method to add two doubles
    public double add(double a, double b) {
        return a + b;
    }
    // Method to display results
    public void display() {
        out.println("Sum of two integers: " + add(5, 10)); // Calls add(int, int)
        out.println("Sum of three integers: " + add(5, 10, 15)); // Calls add(int, int, int)
        out.println("Sum of two doubles: " + add(5.5, 10.2)); // Calls add(double, double)
    }
}

class Example {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.display();
    }
}