public class TicketPrintingSystem {

    public static void main(String[] args) {
        TicketMachine ticketMachine = new TicketMachine();
        Passenger passenger = new Passenger(ticketMachine);
        TicketPaperTechnician ticketPaperTechnician = new TicketPaperTechnician(ticketMachine);

        Thread passengerThread = new Thread(passenger);
        Thread paperTechnicianThread = new Thread(ticketPaperTechnician);

        passengerThread.start();
        paperTechnicianThread.start();
    }
}