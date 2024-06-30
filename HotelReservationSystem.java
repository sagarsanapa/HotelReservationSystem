import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Room {
    String category;
    int roomNumber;
    double pricePerNight;
    boolean isAvailable;

    public Room(String category, int roomNumber, double pricePerNight) {
        this.category = category;
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room{" +
                "category='" + category + '\'' +
                ", roomNumber=" + roomNumber +
                ", pricePerNight=" + pricePerNight +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

class Reservation {
    String customerName;
    Room room;
    int nights;
    double totalCost;

    public Reservation(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.totalCost = room.pricePerNight * nights;
        room.isAvailable = false;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customerName='" + customerName + '\'' +
                ", room=" + room +
                ", nights=" + nights +
                ", totalCost=" + totalCost +
                '}';
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeRooms();
        boolean continueSystem = true;

        while (continueSystem) {
            System.out.println("Welcome to the Hotel Reservation System!");
            System.out.println("1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    continueSystem = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room("Single", 101, 100.0));
        rooms.add(new Room("Double", 102, 150.0));
        rooms.add(new Room("Suite", 103, 250.0));
        rooms.add(new Room("Single", 104, 100.0));
        rooms.add(new Room("Double", 105, 150.0));
    }

    private static void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.next();
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        Room roomToBook = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook != null) {
            Reservation reservation = new Reservation(customerName, roomToBook, nights);
            reservations.add(reservation);
            System.out.println("Reservation successful!");
            System.out.println(reservation);
        } else {
            System.out.println("Room not available or invalid room number.");
        }
    }

    private static void viewBookingDetails() {
        System.out.println("Booking Details:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
