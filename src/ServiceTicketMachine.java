public interface ServiceTicketMachine {

    void printTicket();

    void refillPaper();

    void refillToner();

    int getPaperLevel();

    int getTonerLevel();

    String finalTicketMachine();
}
