package ACTBS.SystemExceptions;

public class ColumnOutOfBoundsException extends RuntimeException {
    public ColumnOutOfBoundsException(){
        super();
    }
    public ColumnOutOfBoundsException(String msg){
        super(msg);
    }
}
