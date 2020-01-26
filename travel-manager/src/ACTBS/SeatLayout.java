package ACTBS;

public enum SeatLayout {
    SMALL(3),
    MEDIUM(4),
    WIDE(10);

    private int column;
    SeatLayout(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
