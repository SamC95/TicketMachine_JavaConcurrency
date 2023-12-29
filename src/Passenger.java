import java.util.Random;

public class Passenger implements Runnable {
    private static final int MAX_TICKET_PRINT = 10;
    private final TicketMachine ticketMachine;

    public Passenger(TicketMachine ticketMachine, ThreadGroup passengerGroup) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        int minimumPrint = 1;
        int ticketsToPrint = new Random().nextInt((MAX_TICKET_PRINT - minimumPrint) + 1) + minimumPrint;

        for (int i = 0; i < ticketsToPrint; i++) {
            try {
                int sleepTime = new Random().nextInt(2000) + 1000;
                Thread.sleep(sleepTime);

                ticketMachine.printTicket();
            }
            catch (InterruptedException error) {
                throw new RuntimeException(error);
            }
        }

        System.out.println("Passenger finished printing tickets");
    }
}