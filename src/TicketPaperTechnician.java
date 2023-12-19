import java.util.Random;

public class TicketPaperTechnician implements Runnable {
    private final TicketMachine ticketMachine;

    public TicketPaperTechnician(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        final int REFILL_ATTEMPTS = 3;

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
                throw new RuntimeException(error);
            }
        }

        System.out.println("Paper technician finished paper refills.");
    }
}
