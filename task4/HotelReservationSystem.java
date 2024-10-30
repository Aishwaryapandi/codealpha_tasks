package task4;
import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private String type;
    private int number;
    private double price;
    private boolean isAvailable;

    public Room(String type, int number, double price) {
        this.type = type;
        this.number = number;
        this.price = price;
        this.isAvailable = true;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("Room Number: %d, Type: %s, Price: $%.2f, Available: %s",
                number, type, price, isAvailable ? "Yes" : "No");
    }
}

class Reservation {
    private Room room;
    private String customerName;

    public Reservation(Room room, String customerName) {
        this.room = room;
        this.customerName = customerName;
    }

    public Room getRoom() {
        return room;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return String.format("Reservation for %s: %s", customerName, room);
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room("Single", 101, 100.00));
        rooms.add(new Room("Double", 102, 150.00));
        rooms.add(new Room("Suite", 201, 300.00));
        rooms.add(new Room("Deluxe", 202, 500.00));
    }

    public ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void makeReservation(String customerName, int roomNumber) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Reservation reservation = new Reservation(room, customerName);
                reservations.add(reservation);
                System.out.printf("Reservation made: %s\n", reservation);
                return;
            }
        }
        System.out.println("Room not available or does not exist.");
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations made.");
        } else {
            System.out.println("Current Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("Available Rooms:");
                    for (Room room : hotel.getAvailableRooms()) {
                        System.out.println(room);
                    }
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room number to reserve: ");
                    int roomNumber = scanner.nextInt();
                    hotel.makeReservation(name, roomNumber);
                    break;

                case 3:
                    hotel.viewReservations();
                    break;

                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


