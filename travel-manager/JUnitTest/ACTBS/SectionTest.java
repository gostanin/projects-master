package ACTBS;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {
    Section testSectionMedium = new Section(2, SeatLayout.MEDIUM, SeatClass.BUSINESS, 100);
    Section testSectionWide = new Section(2, SeatLayout.WIDE, SeatClass.BUSINESS, 100);
    Section testSectionSmall = new Section(2, SeatLayout.SMALL, SeatClass.BUSINESS, 100);
    @Test
    void testGetSeatClass() {
        assertEquals("BUSINESS", testSectionMedium.getSeatClass());
    }

    @Test
    void testHasAvailableSeats() {
        assertEquals(true, testSectionMedium.hasAvailableSpots());
    }

    @Test
    void testBookSeat() {
        assertEquals(true, testSectionMedium.bookSpot(1 ,3));
        assertEquals(false, testSectionMedium.bookSpot(1 ,3));
    }

  

    @Test
    void bookByPreference() {

        assertEquals(true, testSectionMedium.bookByPreference(Position.AISLE));

        assertEquals(true, testSectionMedium.bookByPreference(Position.AISLE));

        assertEquals(true, testSectionMedium.bookByPreference(Position.WINDOW));


        assertEquals(true, testSectionSmall.bookByPreference(Position.AISLE));

        assertEquals(true, testSectionSmall.bookByPreference(Position.AISLE));

        assertEquals(true, testSectionSmall.bookByPreference(Position.WINDOW));

        assertEquals(true, testSectionSmall.bookByPreference(Position.WINDOW));

        //testSectionMedium.printBooked();
        //testSectionSmall.printBooked();
    }
}