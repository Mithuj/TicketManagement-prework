public class TicketVendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;

    public TicketVendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                ticketPool.addTickets();
                Thread.sleep(releaseRate * 1000L);  // Release tickets at the given rate (in seconds)
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor thread interrupted. Exiting...");
        }
    }
}
