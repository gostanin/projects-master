package ACTBS;

import ACTBS.SystemExceptions.*;

public class Spot {
    private String ID;
    private boolean booked;
    private Position position;

    public Spot(int row, int column, Position position) {
        if(row < 1 || row > 100)
            throw new RowOutOfBoundsException("Rows out of range(1,100):Spot");
        if(column < 1 || column > 10)
            throw new ColumnOutOfBoundsException("Columns out of range(1,10):Spot");

        this.position = position;
        this.ID = row + String.valueOf((char)(64+column));
        this.booked = false;
    }

    String getID() {
        return this.ID;
    }

    boolean isBooked() {
        return this.booked;
    }

    public Position getPosition() {
        return position;
    }

    boolean book() {
        if (!this.booked) {
            this.booked = true;
            return true;
        } else {
            return false;
        }
    }
}
