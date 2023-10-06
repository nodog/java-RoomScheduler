package net.konfuzo;

import java.util.ArrayList;
import java.util.Objects;

public class RoomScheduler {

    private final ArrayList<Booking> schedule;

    public RoomScheduler() {
        this.schedule = new ArrayList<Booking>();
    }

    private class Booking {
        Integer startTime;
        Integer endTime;
        String booker;

        public Booking(Integer startTime, Integer endTime, String booker) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.booker = booker;
        }

        public String toString() {
            return "booking start: " + startTime + "  end: " + endTime + "  booker: " + booker;
        }
    }

    public String addBooking(Integer startTime, Integer endTime, String booker) {
        Booking booking = new Booking(startTime, endTime, booker);
        // does this booking conflict with any other bookings?
        // note guaranteed that any endTime is >= any next startTime
        for (Booking aBooking: this.schedule) {
            System.out.println(aBooking);
            if ((aBooking.startTime >= booking.startTime
                    && aBooking.startTime  < booking.endTime)
                || (aBooking.endTime > booking.startTime
                    && aBooking.endTime <= booking.endTime)
                || (booking.startTime >= aBooking.startTime
                    && booking.startTime < aBooking.endTime)) {
                return "FAILURE";
            }
        }
        this.schedule.add(booking);
        return booking.toString();
    }

    public void deleteBookingByStartTime(Integer startTime) {
        this.schedule.removeIf(aBooking -> Objects.equals(aBooking.startTime, startTime));
    }

    public void deleteBookingByBooker(String booker) {
        this.schedule.removeIf(aBooking -> Objects.equals(aBooking.booker, booker));
    }

    public String toString() {
        return "Room Schedule";
    }
}
