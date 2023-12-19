public class TicketPrintingSystem {

    public static void main(String[] args) {
        TicketMachine ticketMachine = new TicketMachine();
        ThreadGroup passengerGroup = new ThreadGroup("Passenger thread group");

        Passenger passenger1 = new Passenger(ticketMachine, passengerGroup);
        Passenger passenger2 = new Passenger(ticketMachine, passengerGroup);
        Passenger passenger3 = new Passenger(ticketMachine, passengerGroup);
        Passenger passenger4 = new Passenger(ticketMachine, passengerGroup);
        TicketPaperTechnician ticketPaperTechnician = new TicketPaperTechnician(ticketMachine);
        TicketTonerTechnician ticketTonerTechnician = new TicketTonerTechnician(ticketMachine);

        Thread passengerThread1 = new Thread(passenger1);
        Thread passengerThread2 = new Thread(passenger2);
        Thread passengerThread3 = new Thread(passenger3);
        Thread passengerThread4 = new Thread(passenger4);
        Thread paperTechnicianThread = new Thread(ticketPaperTechnician);
        Thread tonerTechnicianThread = new Thread(ticketTonerTechnician);

        passengerThread1.start();
        passengerThread2.start();
        passengerThread3.start();
        passengerThread4.start();
        paperTechnicianThread.start();
        tonerTechnicianThread.start();

        try {
            passengerThread1.join();
            passengerThread2.join();
            passengerThread3.join();
            passengerThread4.join();
            paperTechnicianThread.join();
            tonerTechnicianThread.join();
        }
        catch (InterruptedException error) {
            throw new RuntimeException(error);
        }

        System.out.println(ticketMachine.finalTicketMachine());
    }
}