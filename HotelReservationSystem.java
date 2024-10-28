import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isBooked;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        isBooked = true;
    }

    public void cancelBooking() {
        isBooked = false;
    }
}

class Hotel {
    private ArrayList<Room> rooms;

    public Hotel(int numberOfRooms) {
        rooms = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println("Room Number: " + room.getRoomNumber());
            }
        }
    }

    public boolean bookRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isBooked()) {
                room.bookRoom();
                return true;
            }
        }
        return false;
    }

    public void cancelRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isBooked()) {
                room.cancelBooking();
                System.out.println("Booking for Room " + roomNumber + " has been cancelled.");
                return;
            }
        }
        System.out.println("Room " + roomNumber + " is not booked.");
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(10); // Hotel with 10 rooms
        int choice;

        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Room Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter Room Number to Book: ");
                    int bookRoomNumber = scanner.nextInt();
                    if (hotel.bookRoom(bookRoomNumber)) {
                        System.out.println("Room " + bookRoomNumber + " has been booked.");
                    } else {
                        System.out.println("Room " + bookRoomNumber + " is not available.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Room Number to Cancel Booking: ");
                    int cancelRoomNumber = scanner.nextInt();
                    hotel.cancelRoom(cancelRoomNumber);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
