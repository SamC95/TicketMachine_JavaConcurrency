public class TicketPrintingSystem {

    public static void main(String[] args) {
        TicketMachine ticketMachine = new TicketMachine();
        Passenger passenger = new Passenger(ticketMachine);
        TicketPaperTechnician ticketPaperTechnician = new TicketPaperTechnician(ticketMachine);
        TicketTonerTechnician ticketTonerTechnician = new TicketTonerTechnician(ticketMachine);

        Thread passengerThread = new Thread(passenger);
        Thread paperTechnicianThread = new Thread(ticketPaperTechnician);
        Thread tonerTechnicianThread = new Thread(ticketTonerTechnician);

        passengerThread.start();
        paperTechnicianThread.start();
        tonerTechnicianThread.start();
    }
}