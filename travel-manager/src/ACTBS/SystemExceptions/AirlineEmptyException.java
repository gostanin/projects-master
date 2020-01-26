package ACTBS.SystemExceptions;

public class AirlineEmptyException extends RuntimeException {
    public AirlineEmptyException(){
        super();
    }
    public AirlineEmptyException(String msg){
        super(msg);
    }
}
