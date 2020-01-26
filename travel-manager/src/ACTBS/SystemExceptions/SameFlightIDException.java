package ACTBS.SystemExceptions;

public class SameFlightIDException extends RuntimeException {
    public SameFlightIDException(){
        super();
    }
    public SameFlightIDException(String msg){
        super(msg);
    }
}
