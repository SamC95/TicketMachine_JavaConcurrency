import java.util.Random;

public class TicketTonerTechnician implements Runnable {
    private boolean terminateThread = false;
    private final TicketMachine ticketMachine;

    public TicketTonerTechnician(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        final int REFILL_ATTEMPTS = 3;
        int refillsComplete = 1;
        int loopsSpentWaiting = 0;

        while (refillsComplete <= REFILL_ATTEMPTS && !terminateThread) {
            try {
                int sleepTime = new Random().nextInt(5000) + 2000;
                Thread.sleep(sleepTime);

                if (ticketMachine.getTonerLevel() == 0) {
                    ticketMachine.refillToner();
                    System.out.println("Toner technician refilled toner (Refill " + refillsComplete + ")");
                    refillsComplete++;
                    loopsSpentWaiting = 0;
                }
                else if (loopsSpentWaiting == 3) {
                    System.out.println("Passengers finished printing tickets, toner refill attempts stopped.");
                    stopThread();
                }
                else {
                    System.out.println("No toner refill needed... waiting.");
                    loopsSpentWaiting++;
                    Thread.sleep(sleepTime);
                }
            }
            catch (InterruptedException error) {
                throw new RuntimeException(error);
            }
        }

        System.out.println("Toner technician finished toner refills");
    }

    public void stopThread() {
        terminateThread = true;
    }
}
