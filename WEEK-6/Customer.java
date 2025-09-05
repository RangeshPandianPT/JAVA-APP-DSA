// Week 6 - Tutorial Assignment
// Scenario 2: Online Shopping Discounts - Method Overriding

class Customer {
    void getDiscount() {
        System.out.println("Customer discount not defined.");
    }
}

class RegularCustomer extends Customer {
    @Override
    void getDiscount() {
        System.out.println("Regular Customer Discount: 5%");
    }
}

class PremiumCustomer extends Customer {
    @Override
    void getDiscount() {
        System.out.println("Premium Customer Discount: 10%");
    }
}

public class Main {
    public static void main(String[] args) {
        Customer c1 = new RegularCustomer();  
        Customer c2 = new PremiumCustomer(); 

        c1.getDiscount(); 
        c2.getDiscount(); 
    }
}
