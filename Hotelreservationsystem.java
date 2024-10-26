public class Hotelreservationsystem {
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.Scanner;
    
    // Room class representing a hotel room
    class Room {
        private int roomNumber;
        private String type; // e.g., Single, Double, Suite
        private boolean isAvailable;
    
        public Room(int roomNumber, String type) {
            this.roomNumber = roomNumber;
            this.type = type;
            this.isAvailable = true;
        }
    
        public int getRoomNumber() {
            return roomNumber;
        }
    
        public String getType() {
            return type;
        }
    
        public boolean isAvailable() {
            return isAvailable;
        }
    
        public void bookRoom() {
            this.isAvailable = false;
        }
    
        public void releaseRoom() {
            this.isAvailable = true;
        }
    }
    
    // Customer class representing a customer
    class Customer {
        private String name;
        private String contactNumber;
    
        public Customer(String name, String contactNumber) {
            this.name = name;
            this.contactNumber = contactNumber;
        }
    
        public String getName() {
            return name;
        }
    
        public String getContactNumber() {
            return contactNumber;
        }
    }
    
    // Reservation class representing a room reservation
    class Reservation {
        private Customer customer;
        private Room room;
        private Date startDate;
        private Date endDate;
    
        public Reservation(Customer customer, Room room, Date startDate, Date endDate) {
            this.customer = customer;
            this.room = room;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    
        public Customer getCustomer() {
            return customer;
        }
    
        public Room getRoom() {
            return room;
        }
    
        public Date getStartDate() {
            return startDate;
        }
    
        public Date getEndDate() {
            return endDate;
        }
    
        @Override
        public String toString() {
            return "Reservation{" +
                    "customer=" + customer.getName() +
                    ", room=" + room.getRoomNumber() +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }
    
    // Hotel class managing rooms and reservations
    class Hotel {
        private ArrayList<Room> rooms;
        private ArrayList<Reservation> reservations;
    
        public Hotel() {
            rooms = new ArrayList<>();
            reservations = new ArrayList<>();
        }
    
        public void addRoom(Room room) {
            rooms.add(room);
        }
    
        public ArrayList<Room> getRooms() {
            return rooms;
        }
    
        public void makeReservation(Customer customer, Room room, Date startDate, Date endDate) {
            if (room.isAvailable()) {
                Reservation reservation = new Reservation(customer, room, startDate, endDate);
                reservations.add(reservation);
                room.bookRoom();
                System.out.println("Reservation made successfully: " + reservation);
            } else {
                System.out.println("Room is not available.");
            }
        }
    
        // Method to view all reservations
        public void viewReservations() {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
    
    // Main class for the hotel reservation system
    public class HotelReservationSystem {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Hotel hotel = new Hotel();
    
            // Adding some rooms to the hotel
            hotel.addRoom(new Room(101, "Single"));
            hotel.addRoom(new Room(102, "Double"));
            hotel.addRoom(new Room(103, "Suite"));
    
            // Main menu loop
            while (true) {
                System.out.println("1. Make a reservation");
                System.out.println("2. View reservations");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
    
                switch (choice) {
                    case 1:
                        System.out.println("Enter customer name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter contact number:");
                        String contactNumber = scanner.nextLine();
    
                        Customer customer = new Customer(name, contactNumber);
                        // For simplicity, choose the first available room
                        Room room = hotel.getRooms().stream()
                                .filter(Room::isAvailable)
                                .findFirst()
                                .orElse(null);
    
                        // Placeholder for dates (improving this is advisable)
                        Date startDate = new Date(); // Current date
                        Date endDate = new Date(System.currentTimeMillis() + 86400000); // One day later
    
                        if (room != null) {
                            hotel.makeReservation(customer, room, startDate, endDate);
                        } else {
                            System.out.println("No available rooms.");
                        }
                        break;
    
                    case 2:
                        System.out.println("Current reservations:");
                        hotel.viewReservations();
                        break;
    
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
    
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
     

}