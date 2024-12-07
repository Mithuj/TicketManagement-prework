import java.util.Scanner;

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Real-Time Ticket Management System!");

        System.out.print("Would you like to load the existing configuration from file? (yes/no): ");
        String choice = scanner.nextLine();

        int totalTickets = 0, ticketReleaseRate = 0, customerRetrievalRate = 0, maxTicketCapacity = 0;

        if (choice.equalsIgnoreCase("no")) {
            System.out.println("Starting new configuration setup ...");
            try {
                System.out.print("Enter total tickets (must be greater than 0): ");
                totalTickets = Integer.parseInt(scanner.nextLine());
                if (totalTickets <= 0) throw new IllegalArgumentException("Total tickets must be greater than 0!");

                System.out.print("Enter ticket release rate (in seconds, must be greater than 0): ");
                ticketReleaseRate = Integer.parseInt(scanner.nextLine());
                if (ticketReleaseRate <= 0) throw new IllegalArgumentException("Ticket release rate must be greater than 0!");

                System.out.print("Enter customer retrieval rate (in seconds, must be greater than 0): ");
                customerRetrievalRate = Integer.parseInt(scanner.nextLine());
                if (customerRetrievalRate <= 0) throw new IllegalArgumentException("Customer retrieval rate must be greater than 0!");

                System.out.print("Enter max ticket capacity (must be greater than total tickets and greater than 0): ");
                maxTicketCapacity = Integer.parseInt(scanner.nextLine());
                if (maxTicketCapacity <= totalTickets || maxTicketCapacity <= 0)
                    throw new IllegalArgumentException("Max ticket capacity must be greater than total tickets and greater than 0!");

                System.out.println("Configuration saved successfully.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.exit(0);
            }
        } else {
            System.out.println("Loading configuration from file (mock implementation)...");
            // Load logic (not implemented here)
        }

        TicketPool ticketPool = new TicketPool(totalTickets, maxTicketCapacity);

        Thread vendorThread = new Thread(new TicketVendor(ticketPool, ticketReleaseRate));
        Thread customerThread = new Thread(new TicketCustomer(ticketPool, customerRetrievalRate));

        vendorThread.start();
        customerThread.start();

        System.out.println("System is running. Enter 'stop' to terminate the system.");
        while (!scanner.nextLine().equalsIgnoreCase("stop")) {
            System.out.println("Invalid command! Type 'stop' to exit.");
        }

        System.out.println("Terminating system...");
        vendorThread.interrupt();
        customerThread.interrupt();
    }
}
