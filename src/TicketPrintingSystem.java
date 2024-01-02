public class TicketPrintingSystem {

    public static void main(String[] args) {
        TicketMachine ticketMachine = new TicketMachine();
        ThreadGroup passengerGroup = new ThreadGroup("Passenger thread group");

        // Creates the appropriate passengers and technicians
        Passenger passenger1 = new Passenger(ticketMachine, passengerGroup, 1);
        Passenger passenger2 = new Passenger(ticketMachine, passengerGroup, 2);
        Passenger passenger3 = new Passenger(ticketMachine, passengerGroup, 3);
        Passenger passenger4 = new Passenger(ticketMachine, passengerGroup, 4);
        TicketPaperTechnician ticketPaperTechnician = new TicketPaperTechnician(ticketMachine);
        TicketTonerTechnician ticketTonerTechnician = new TicketTonerTechnician(ticketMachine);

        // Assigns each passenger/technician to their own thread
        Thread passengerThread1 = new Thread(passenger1);
        Thread passengerThread2 = new Thread(passenger2);
        Thread passengerThread3 = new Thread(passenger3);
        Thread passengerThread4 = new Thread(passenger4);
        Thread paperTechnicianThread = new Thread(ticketPaperTechnician);
        Thread tonerTechnicianThread = new Thread(ticketTonerTechnician);

        // Start all the threads
        passengerThread1.start();
        passengerThread2.start();
        passengerThread3.start();
        passengerThread4.start();
        paperTechnicianThread.start();
        tonerTechnicianThread.start();

        // Checks if every thread has finished execution
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

        /*Prints a final status message informing that threads have finished running
         and providing the amount of tickets printed and remaining values for the paper and toner*/
        System.out.println(ticketMachine.finalTicketMachine());
    }
}