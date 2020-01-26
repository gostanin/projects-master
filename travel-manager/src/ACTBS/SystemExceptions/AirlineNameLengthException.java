package ACTBS.SystemExceptions;

public class AirlineNameLengthException extends RuntimeException {
    public AirlineNameLengthException(){
        super();
    }
    public AirlineNameLengthException(String msg){
        super(msg);
    }
}
