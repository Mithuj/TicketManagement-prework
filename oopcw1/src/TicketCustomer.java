public class TicketCustomer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public TicketCustomer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                ticketPool.removeTicket();
                Thread.sleep(retrievalRate * 1000L);  // Retrieve tickets at the given rate (in seconds)
            }
        } catch (InterruptedException e) {
            System.out.println("Customer thread interrupted. Exiting...");
        }
    }
}
