package ACTBS;

import ACTBS.SystemExceptions.ColumnOutOfBoundsException;
import ACTBS.SystemExceptions.RowOutOfBoundsException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpotTest {
    @Test
    void testRowOutOfBoundaries() {
        assertThrows(RowOutOfBoundsException.class, () -> new Spot(-1, 1, Position.WINDOW));
        assertThrows(RowOutOfBoundsException.class, () -> new Spot(101, 1, Position.WINDOW));
    }

    @Test
    void testColumnOutOfBoundaries() {
        assertThrows(ColumnOutOfBoundsException.class, () -> new Spot(1, -1, Position.WINDOW));
        assertThrows(ColumnOutOfBoundsException.class, () -> new Spot(1, 11, Position.WINDOW));
    }

    @Test
    void testGetID() {
        assertEquals("1A", new Spot(1, 1, Position.WINDOW).getID());
        assertEquals("100J", new Spot(100, 10, Position.WINDOW).getID());
    }

    @Test
    void testIsBooked() {
        assertEquals( false, new Spot(1, 1, Position.WINDOW).isBooked());
        Spot bookSpot = new Spot(1,1, Position.WINDOW);
        bookSpot.book();
        assertEquals(true, bookSpot.isBooked());
    }

    @Test
    void testBook() {
        Spot bookSpot = new Spot(1,1, Position.WINDOW);
        assertEquals( true, bookSpot.book());
        assertEquals(false, bookSpot.book());
    }

}