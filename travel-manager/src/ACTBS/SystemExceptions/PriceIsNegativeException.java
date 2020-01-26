package ACTBS.SystemExceptions;

public class PriceIsNegativeException extends RuntimeException {
    public PriceIsNegativeException(){
        super();
    }
    public PriceIsNegativeException(String msg){
        super(msg);
    }
}
