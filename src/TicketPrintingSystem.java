public class TicketPrintingSystem {

    public static void main(String[] args) {
        TicketMachine ticketMachine = new TicketMachine();
        Passenger passenger = new Passenger(ticketMachine);

        Thread passengerThread = new Thread(passenger);

        passengerThread.start();
    }
}