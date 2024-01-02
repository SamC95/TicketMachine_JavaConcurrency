public interface ServiceTicketMachine {

    void printTicket(int passengerNum) throws InterruptedException;

    void refillPaper();

    void refillToner();

    int getPaperLevel();

    int getTonerLevel();

    int getTicketsPrinted();

    String finalTicketMachine();
}
