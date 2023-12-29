public class TicketMachine implements ServiceTicketMachine {

    private static final int INITIAL_PAPER = 3;
    private static final int MAX_PAPER_CAPACITY = 5;
    private static final int MAX_TONER_CAPACITY = 50;
    private static final int TONER_COST = 10;

    private int paperLevel;
    private int tonerLevel;
    private int ticketsPrinted;

    public TicketMachine() {
        this.paperLevel = INITIAL_PAPER;
        this.tonerLevel = MAX_TONER_CAPACITY;
    }

    /*Checks the levels of paper and toner, if neither are empty then prints a ticket
     and reduces the levels appropriately, if one is empty then print the appropriate message*/
    @Override
    public synchronized void printTicket() throws InterruptedException {
        while(paperLevel == 0 || tonerLevel == 0) {
            try {
                this.wait(3000);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (paperLevel > 0 && tonerLevel > 0) {
            Ticket ticket = new Ticket();
            System.out.println("Printing Ticket #" + ticket.getNumOfTickets());

            paperLevel--;
            tonerLevel -= TONER_COST;

            ticketsPrinted++;
        }
    }

    // Checks if the paper is empty, if so refills it to max capacity (10)
    @Override
    public synchronized void refillPaper() {
        if (paperLevel < MAX_PAPER_CAPACITY) {
            int addPaper = MAX_PAPER_CAPACITY - paperLevel;

            System.out.println("Refilling " + addPaper + " sheets of paper");
            paperLevel = MAX_PAPER_CAPACITY;
        }
        else {
            System.out.println("Paper capacity already at maximum");
        }
    }


    // Checks if toner is empty, if so refills it to max capacity (100)
    @Override
    public synchronized void refillToner() {
        if (tonerLevel < MAX_TONER_CAPACITY) {
            int refillToner = MAX_TONER_CAPACITY - tonerLevel;

            System.out.println("Refilling " + refillToner + " to toner levels");
            tonerLevel = MAX_TONER_CAPACITY;
        }
        else {
            System.out.println("Toner capacity already at maximum");
        }
    }

    @Override
    public int getPaperLevel() {
        return paperLevel;
    }

    @Override
    public int getTonerLevel() {
        return tonerLevel;
    }

    @Override
    public int getTicketsPrinted() {
        return ticketsPrinted;
    }

    @Override
    public String finalTicketMachine() {
        return  "--------------------------------------\n" +
                "All threads have finished execution \n" +
                "Tickets Printed: " + getTicketsPrinted() + "\n" +
                "Paper Remaining: " + getPaperLevel() + "\n" +
                "Toner Remaining: " + getTonerLevel();

    }
}
