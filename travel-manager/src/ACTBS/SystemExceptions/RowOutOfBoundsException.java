package ACTBS.SystemExceptions;

public class RowOutOfBoundsException extends RuntimeException {
    public RowOutOfBoundsException(){
        super();
    }
    public RowOutOfBoundsException(String msg){
        super(msg);
    }
}
