package exception;

public class InvalidTicketException extends RuntimeException {

    public InvalidTicketException(String s) {
        super(s);
    }
}
