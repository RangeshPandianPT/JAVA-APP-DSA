import java.util.*;

class Product 
{
    private String name;
    private double price;
    private int stock;

    Product(String name, double price, int stock) 
	{
        this.name = name; this.price = price; this.stock = stock;
    }
    String getName() { return name; }
    double getPrice() { return price; }
    int getStock() { return stock; }
    void reduceStock(int qty) { stock -= qty; }
    void increaseStock(int qty) { stock += qty; }
    public String toString() { return name + " | Price: " + price + " | Stock: " + stock; }
}

class CartItem 
{
    private Product product;
    private int quantity;

    CartItem(Product product, int quantity) 
	{
        this.product = product; this.quantity = quantity;
    }
    double getItemTotal() { return product.getPrice() * quantity; }
    Product getProduct() { return product; }
    int getQuantity() { return quantity; }
    void increaseQty(int qty) { quantity += qty; }
    void decreaseQty(int qty) { quantity -= qty; }
    public String toString() { return product.getName() + " x" + quantity + " = " + getItemTotal(); }
}

class ShoppingCart 
{
    private ArrayList<CartItem> items = new ArrayList<>();

    void addItem(Product p, int qty) 
	{
        if (p.getStock() < qty) 
		{
            System.out.println("Not enough stock for " + p.getName());
            return;
        }
        for (CartItem c : items) 
		{
            if (c.getProduct().equals(p)) { c.increaseQty(qty); p.reduceStock(qty); return; }
        }
        items.add(new CartItem(p, qty));
        p.reduceStock(qty);
    }

    void removeItem(Product p, int qty) 
	{
        for (Iterator<CartItem> it = items.iterator(); it.hasNext();) 
		{
            CartItem c = it.next();
            if (c.getProduct().equals(p)) 
			{
                if (qty >= c.getQuantity()) { p.increaseStock(c.getQuantity()); it.remove(); }
                else { c.decreaseQty(qty); p.increaseStock(qty); }
                return;
            }
        }
        System.out.println("Item not found in cart");
    }

    double calculateTotal() 
	{
        double total = 0;
        for (CartItem c : items) total += c.getItemTotal();
        if (total > 1000) total *= 0.9; // 10% discount if total > 1000
        return total;
    }

    void viewCart() 
	{
        if (items.isEmpty()) System.out.println("Cart is empty");
        else for (CartItem c : items) System.out.println(c);
        System.out.println("Total: " + calculateTotal());
    }
}

class User 
{
    private String name;
    private ShoppingCart cart;

    User(String name) { this.name = name; this.cart = new ShoppingCart(); }
    ShoppingCart getCart() { return cart; }
    String getName() { return name; }
}

public class ShoppingApp 
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product p1 = new Product("Laptop", 50000, 5);
        Product p2 = new Product("Phone", 20000, 10);
        Product p3 = new Product("Headphones", 2000, 15);
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(p1, p2, p3));

        User user = new User("Alice");

        while (true) 
		{
            System.out.println("\n1.View Products  2.Add to Cart  3.Remove from Cart  4.View Cart  5.Exit");
            int choice = sc.nextInt();
            if (choice == 5) break;

            switch (choice) 
			{
                case 1 -> products.forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter product index (0-" + (products.size()-1) + "): ");
                    int i = sc.nextInt();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    if (i >= 0 && i < products.size()) user.getCart().addItem(products.get(i), qty);
                }
                case 3 -> 
				{
                    System.out.print("Enter product index (0-" + (products.size()-1) + "): ");
                    int i = sc.nextInt();
                    System.out.print("Enter quantity to remove: ");
                    int qty = sc.nextInt();
                    if (i >= 0 && i < products.size()) user.getCart().removeItem(products.get(i), qty);
                }
                case 4 -> user.getCart().viewCart();
                default -> System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}
