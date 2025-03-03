import java.util.*;

class TicketBookingSystem {
    private final LinkedList<Integer> availableSeats = new LinkedList<>();
    private final Object lock = new Object();

    public TicketBookingSystem(int totalSeats) {
        for (int i = 1; i <= totalSeats; i++) {
            availableSeats.add(i);
        }
    }

    public void bookTicket(String passengerName, String trainName, String seatPreference, boolean isVIP) {
        synchronized (lock) {
            if (!availableSeats.isEmpty()) {
                int seat = availableSeats.removeFirst();
                System.out.println(
                    "Passenger: " + passengerName + 
                    ", Train: " + trainName + 
                    ", Seat Preference: " + seatPreference + 
                    ", Seat Number: " + seat + 
                    " -> Booking Confirmed!");
            } else {
                System.out.println(
                    "Passenger: " + passengerName + 
                    ", Train: " + trainName + 
                    ", Seat Preference: " + seatPreference + 
                    " -> Booking Failed: No seats available.");
            }
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final String passengerName;
    private final String trainName;
    private final String seatPreference;
    private final boolean isVIP;

    BookingThread(TicketBookingSystem system, String passengerName, String trainName, String seatPreference, boolean isVIP) {
        this.system = system;
        this.passengerName = passengerName;
        this.trainName = trainName;
        this.seatPreference = seatPreference;
        this.isVIP = isVIP;
        if (isVIP) {
            setPriority(Thread.MAX_PRIORITY);
        } else {
            setPriority(Thread.NORM_PRIORITY);
        }
    }

    public void run() {
        system.bookTicket(passengerName, trainName, seatPreference, isVIP);
    }
}

public class IndianRailways {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of available seats
        System.out.print("Enter the number of available seats on the train: ");
        int totalSeats = scanner.nextInt();

        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        System.out.print("Enter the number of passengers: ");
        int numPassengers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<BookingThread> threads = new ArrayList<>();

        // Take passenger details for booking
        for (int i = 0; i < numPassengers; i++) {
            System.out.print("Enter Passenger Name: ");
            String passengerName = scanner.nextLine();

            System.out.print("Enter Train Name: ");
            String trainName = scanner.nextLine();

            System.out.print("Enter Seat Preference (Window/Middle/Aisle): ");
            String seatPreference = scanner.nextLine();

            System.out.print("Is this passenger VIP? (yes/no): ");
            boolean isVIP = scanner.nextLine().equalsIgnoreCase("yes");

            threads.add(new BookingThread(system, passengerName, trainName, seatPreference, isVIP));
        }

        // Start all threads
        for (BookingThread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (BookingThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
