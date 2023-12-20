public interface ServiceTicketMachine {

    void printTicket() throws InterruptedException;

    void refillPaper();

    void refillToner();

    int getPaperLevel();

    int getTonerLevel();

    String finalTicketMachine();
}
