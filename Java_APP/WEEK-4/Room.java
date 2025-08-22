import java.io.*;
import java.util.*;

class Room 
{
    private int roomNo;
    private String type;
    private double price;
    private boolean available = true;

    Room(int roomNo, String type, double price) 
	{
        this.roomNo = roomNo; this.type = type; this.price = price;
    }
    int getRoomNo() { return roomNo; }
    String getType() { return type; }
    double getPrice() { return price; }
    boolean isAvailable() { return available; }
    void setAvailable(boolean status) { available = status; }

    public String toString() {
        return "Room " + roomNo + " (" + type + ") - Rs." + price + " - " + (available ? "Available" : "Booked");
    }
}

class Customer {
    private String name, contact;
    Customer(String name, String contact) { this.name = name; this.contact = contact; }
    public String toString() { return name + " (" + contact + ")"; }
}

class Reservation 
{
    private Customer customer;
    private Room room;
    private String date;

    Reservation(Customer c, Room r, String date) 
	{
        this.customer = c; this.room = r; this.date = date;
    }

    Customer getCustomer() { return customer; }
    Room getRoom() { return room; }
    String getDate() { return date; }

    public String toString() 
	{
        return "Reservation: " + customer + " | " + room.getRoomNo() + " | " + date;
    }
}

interface ReservationOps 
{
    void book(Room r, Customer c, String date) throws IOException;
    void cancel(int roomNo, String date) throws IOException;
    void viewReservations();
}

class ReservationManager implements ReservationOps 
{
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<Room> rooms;

    ReservationManager(ArrayList<Room> rooms) { this.rooms = rooms; loadReservations(); }

    private void saveReservations() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("reservations.dat"))) 
		{
            oos.writeObject(reservations);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadReservations() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reservations.dat"))) 
		{
            reservations = (ArrayList<Reservation>) ois.readObject();
            for (Reservation r : reservations) r.getRoom().setAvailable(false);
        } catch (Exception e) { reservations = new ArrayList<>(); }
    }

    public void book(Room r, Customer c, String date) throws IOException 
	{
        if (!r.isAvailable()) { System.out.println("Room already booked!"); return; }
        Reservation res = new Reservation(c, r, date);
        reservations.add(res); r.setAvailable(false);
        saveReservations();
        System.out.println("Booking confirmed: " + res);
    }

    public void cancel(int roomNo, String date) throws IOException 
	{
        Reservation target = null;
        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNo() == roomNo && res.getDate().equals(date)) 
			{
                target = res; break;
            }
        }
        if (target != null) {
            reservations.remove(target);
            target.getRoom().setAvailable(true);
            saveReservations();
            System.out.println("Reservation canceled.");
        } else System.out.println("No such reservation.");
    }

    public void viewReservations() {
        if (reservations.isEmpty()) System.out.println("No reservations found.");
        else reservations.forEach(System.out::println);
    }

    public void searchAvailable(String date) {
        System.out.println("Available rooms on " + date + ":");
        for (Room r : rooms) if (r.isAvailable()) System.out.println(r);
    }
}

public class HotelReservationSystem 
{
    public static void main(String[] args) 
	{
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Single", 2000));
        rooms.add(new Room(102, "Double", 3500));
        rooms.add(new Room(201, "Suite", 8000));

        ReservationManager manager = new ReservationManager(rooms);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.View Rooms  2.Search Available  3.Book  4.Cancel  5.View Reservations  6.Exit");
            int ch = sc.nextInt();
            if (ch == 6) break;

            switch (ch) {
                case 1 -> rooms.forEach(System.out::println);
                case 2 -> { System.out.print("Enter date: "); manager.searchAvailable(sc.next()); }
                case 3 -> {
                    System.out.print("Enter room no: "); int rno = sc.nextInt();
                    System.out.print("Enter date: "); String date = sc.next();
                    sc.nextLine();
                    System.out.print("Enter name: "); String name = sc.nextLine();
                    System.out.print("Enter contact: "); String contact = sc.nextLine();
                    Room r = rooms.stream().filter(x -> x.getRoomNo()==rno).findFirst().orElse(null);
                    if (r != null) try { manager.book(r, new Customer(name, contact), date); } catch (IOException e) {}
                }
                case 4 -> {
                    System.out.print("Enter room no: "); int rno = sc.nextInt();
                    System.out.print("Enter date: "); String date = sc.next();
                    try { manager.cancel(rno, date); } catch (IOException e) {}
                }
                case 5 -> manager.viewReservations();
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
