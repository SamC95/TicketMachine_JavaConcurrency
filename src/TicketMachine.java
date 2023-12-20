public class TicketMachine implements ServiceTicketMachine {

    private static final int INITIAL_PAPER = 3;
    private static final int MAX_PAPER_CAPACITY = 10;
    private static final int MAX_TONER_CAPACITY = 100;
    private static final int TONER_COST = 25;

    private int paperLevel;
    private int tonerLevel;

    public TicketMachine() {
        this.paperLevel = INITIAL_PAPER;
        this.tonerLevel = MAX_TONER_CAPACITY;
    }

    /*Checks the levels of paper and toner, if neither are empty then prints a ticket
     and reduces the levels appropriately, if one is empty then print the appropriate message*/
    @Override
    public synchronized void printTicket() {
        if (paperLevel > 0 && tonerLevel > 0) {
            Ticket ticket = new Ticket();
            System.out.println("Printing Ticket #" + ticket.getNumOfTickets());
            paperLevel--;

            tonerLevel -= TONER_COST;
        }
        else if (paperLevel == 0) {
            System.out.println("No paper remaining, Unable to print ticket");
        }
        else {
            System.out.println("Printer out of toner, Unable to print ticket");
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
    public String finalTicketMachine() {
        return  "--------------------------------------\n" +
                "All threads have finished execution \n" +
                "Paper Remaining: " + getPaperLevel() + "\n" +
                "Toner Remaining: " + getTonerLevel();

    }
}
