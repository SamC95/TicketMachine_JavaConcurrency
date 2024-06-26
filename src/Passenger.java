import java.util.Random;

public class Passenger implements Runnable {
    private static final int MAX_TICKET_PRINT = 8;
    private final TicketMachine ticketMachine;
    private final int passengerNum;

    public Passenger(TicketMachine ticketMachine, ThreadGroup passengerGroup, int passengerNum) {
        this.ticketMachine = ticketMachine;
        this.passengerNum = passengerNum;
    }

    @Override
    public synchronized void run() {
        int minimumPrint = 1;
        int ticketsToPrint = new Random().nextInt((MAX_TICKET_PRINT - minimumPrint) + 1) + minimumPrint;

        for (int i = 0; i < ticketsToPrint; i++) {
            try {
                int sleepTime = new Random().nextInt(2000) + 1000;
                this.wait(sleepTime);

                ticketMachine.printTicket(passengerNum);
            }
            catch (InterruptedException error) {
                throw new RuntimeException(error);
            }
        }

        System.out.println("Passenger " + this.passengerNum  + " finished printing tickets");
    }
}