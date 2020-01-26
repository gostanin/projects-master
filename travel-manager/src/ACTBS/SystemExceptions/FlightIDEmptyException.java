package ACTBS.SystemExceptions;

public class FlightIDEmptyException extends RuntimeException {
    public FlightIDEmptyException(){
        super();
    }
    public FlightIDEmptyException(String msg){
        super(msg);
    }
}
