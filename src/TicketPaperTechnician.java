import java.util.Random;

public class TicketPaperTechnician implements Runnable {
    private static int REFILL_ATTEMPTS = 3;
    private TicketMachine ticketMachine;

    public TicketPaperTechnician(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        for (int attempt = 1; attempt <= REFILL_ATTEMPTS; attempt++) {
            try {
                int sleepTime = new Random().nextInt(5000) + 2000;
                Thread.sleep(sleepTime);

                if (ticketMachine.getPaperLevel() == 0) {
                    ticketMachine.refillPaper();
                    System.out.println("Paper technician refilled paper (Attempt " + attempt + ")");
                }
                else {
                    System.out.println("No paper refill needed (Attempt " + attempt + ")");
                }
            }
            catch (InterruptedException error) {
                System.out.println("Error with paper Technician tasks");
            }
        }

        System.out.println("Paper technician finished paper refills.");
    }
}
