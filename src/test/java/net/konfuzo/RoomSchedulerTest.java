package net.konfuzo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomSchedulerTest {
    RoomScheduler rs;

    @BeforeEach
    void setUp() {
        rs = new RoomScheduler();
    }

    @Test
    void testToString() {
        assertEquals("Room Schedule", rs.toString());
    }

    @Test
    void shouldAddBooking() {
        String booking = rs.addBooking(2, 30, "Maudie");
        assertEquals("booking start: 2  end: 30  booker: Maudie", booking);
    }

    @Test
    void shouldAddTwoDistinctBookings() {
        String booking1 = rs.addBooking(2, 30, "Maudie");
        assertEquals("booking start: 2  end: 30  booker: Maudie", booking1);
        String booking2 = rs.addBooking(32, 60, "Megan");
        assertEquals("booking start: 32  end: 60  booker: Megan", booking2);
    }

    @Test
    void shouldFailToAddInsideAnotherBooking() {
        String booking1 = rs.addBooking(2, 30, "Maudie");
        assertEquals("booking start: 2  end: 30  booker: Maudie", booking1);
        String booking2 = rs.addBooking(10, 20, "Megan");
        assertEquals("FAILURE", booking2);
    }

    @Test
    void shouldFailToAddTwoOverlappingBookings() {
        String booking1 = rs.addBooking(2, 30, "Maudie");
        assertEquals("booking start: 2  end: 30  booker: Maudie", booking1);
        String booking2 = rs.addBooking(22, 60, "Megan");
        assertEquals("FAILURE", booking2);
    }

    @Test
    void shouldFailToAddTwoOtherOverlappingBookings() {
        String booking1 = rs.addBooking(30, 60, "Maudie");
        assertEquals("booking start: 30  end: 60  booker: Maudie", booking1);
        String booking2 = rs.addBooking(22, 32, "Megan");
        assertEquals("FAILURE", booking2);
    }

    @Test
    void shouldFailToAddTwoOverlappingBookingsButStillAddDistinctOne() {
        String booking1 = rs.addBooking(2, 30, "Maudie");
        assertEquals("booking start: 2  end: 30  booker: Maudie", booking1);
        String booking2 = rs.addBooking(22, 60, "Megan");
        assertEquals("FAILURE", booking2);
        String booking3 = rs.addBooking(42, 60, "Megan");
        assertEquals("booking start: 42  end: 60  booker: Megan", booking3);
    }

    @Test
    void shouldDeleteBookingByStartTime() {
        String booking1 = rs.addBooking(2, 30, "Maudie");
        assertEquals("booking start: 2  end: 30  booker: Maudie", booking1);
        String booking2 = rs.addBooking(2, 30, "Molina");
        assertEquals("FAILURE", booking2);
        rs.deleteBookingByStartTime(2);
        String booking3 = rs.addBooking(2, 30, "Molina");
        assertEquals("booking start: 2  end: 30  booker: Molina", booking3);

    }

    @Test
    void shouldDeleteBookingByBooker() {
        String booking1 = rs.addBooking(2, 30, "Maudie");
        assertEquals("booking start: 2  end: 30  booker: Maudie", booking1);
        String booking2 = rs.addBooking(2, 30, "Molina");
        assertEquals("FAILURE", booking2);
        rs.deleteBookingByBooker("Maudie");
        String booking3 = rs.addBooking(2, 30, "Molina");
        assertEquals("booking start: 2  end: 30  booker: Molina", booking3);
    }

    @Test
    void shouldDeleteAllBookingsByBooker() {
        String booking1 = rs.addBooking(2, 4, "Maudie");
        assertEquals("booking start: 2  end: 4  booker: Maudie", booking1);
        String booking2 = rs.addBooking(4, 6, "Molina");
        assertEquals("booking start: 4  end: 6  booker: Molina", booking2);
        String booking3 = rs.addBooking(6, 8, "Maudie");
        assertEquals("booking start: 6  end: 8  booker: Maudie", booking3);
        rs.deleteBookingByBooker("Maudie");
        String booking4 = rs.addBooking(7, 10, "Minnie");
        assertEquals("booking start: 7  end: 10  booker: Minnie", booking4);
    }

}