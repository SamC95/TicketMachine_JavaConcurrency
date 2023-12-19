public class Ticket {
    private static int ticketCount = 1;
    private final int numOfTickets;

    public Ticket() {
        this.numOfTickets = ticketCount++;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }
}
