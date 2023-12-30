import java.util.Random;

public class TicketPaperTechnician implements Runnable {
    private boolean terminateThread = false;
    private final TicketMachine ticketMachine;

    public TicketPaperTechnician(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    /* Loops through checking if paper needs a refill, if it does then it will refill and increment the number of
    *  completed refills, as well as resetting loopsSpentWaiting to 0. If loopsSpentWaiting occurs 5 times without
    *  a successful refill, then the paper technician will stop attempting to refill and complete execution.
    *  If the technician checks to see if paper refills are needed, and they are not, it will go into a waiting state
    *  and increment loopsSpentWaiting by 1
    */
    @Override
    public synchronized void run() {
        final int REFILL_ATTEMPTS = 3;
        int refillsComplete = 1;
        int loopsSpentWaiting = 0;

        while (refillsComplete <= REFILL_ATTEMPTS && (!terminateThread)) {
            try {
                int sleepTime = new Random().nextInt(5000) + 2000;
                this.wait(sleepTime);

                if (ticketMachine.getPaperLevel() == 0) {
                    ticketMachine.refillPaper();
                    System.out.println("Paper technician refilled paper (Refill " + refillsComplete + ")");
                    refillsComplete++;
                    loopsSpentWaiting = 0;
                }
                else if (loopsSpentWaiting == 4) {
                    System.out.println("Passengers finished printing tickets, paper refill attempts stopped.");
                    stopThread();
                }
                else {
                    System.out.println("No paper refill needed... waiting.");
                    loopsSpentWaiting++;
                    this.wait(sleepTime);
                }
            } catch (InterruptedException error) {
                throw new RuntimeException(error);
            }
        }

        System.out.println("Paper technician finished paper refills.");
    }

    public void stopThread() {
        terminateThread = true;
    }
}
