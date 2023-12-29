public interface ServiceTicketMachine {

    void printTicket() throws InterruptedException;

    void refillPaper();

    void refillToner();

    int getPaperLevel();

    int getTonerLevel();

    int getTicketsPrinted();

    String finalTicketMachine();
}
