package Management;

import java.util.HashSet;

public interface BookingInterface {
    void addBooking(int bookingID, String name, String number, String type);

    void removeBooking(int bookingID);

    void editBooking(int bookingID, String name, String number, String type);

    HashSet<Booking> allBookings();
}
