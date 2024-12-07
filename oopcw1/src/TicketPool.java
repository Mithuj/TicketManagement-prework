import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int maxCapacity;

    public TicketPool(int initialTickets, int maxCapacity) {
        this.maxCapacity = maxCapacity;
        for (int i = 0; i < initialTickets; i++) {
            tickets.add(1);
        }
    }

    public synchronized void addTickets() throws InterruptedException {
        while (tickets.size() >= maxCapacity) {
            wait();
        }
        tickets.add(1);
        System.out.println("INFO: Ticket added. Total tickets: " + tickets.size());
        notifyAll();
    }

    public synchronized void removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait();
        }
        tickets.poll();
        System.out.println("INFO: Ticket retrieved by customer. Tickets remaining: " + tickets.size());
        notifyAll();
    }
}
