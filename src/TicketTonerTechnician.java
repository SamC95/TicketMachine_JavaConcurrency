import java.util.Random;

public class TicketTonerTechnician implements Runnable {
    private final TicketMachine ticketMachine;

    public TicketTonerTechnician(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        final int REFILL_ATTEMPTS = 3;

        for (int attempt = 0; attempt <= REFILL_ATTEMPTS; attempt++) {
            try {
                int sleepTime = new Random().nextInt(5000) + 2000;
                Thread.sleep(sleepTime);

                if (ticketMachine.getTonerLevel() == 0) {
                    ticketMachine.refillToner();
                    System.out.println("Toner technician refilled toner (Attempt " + attempt + ")");
                }
                else {
                    System.out.println("No toner refill needed (Attempt " + attempt + ")");
                }
            }
            catch (InterruptedException error) {
                throw new RuntimeException(error);
            }
        }

        System.out.println("Toner technician finished toner refills");
    }
}
