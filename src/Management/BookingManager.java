package Management;

import java.util.HashSet;

public class BookingManager implements BookingInterface{
    private HashSet<Booking> bookingSet;

    public BookingManager(){
        this.bookingSet = new HashSet<>();
    }
    @Override
    public void addBooking(int bookingID, String name, String number, String type) {
    Booking booking = new Booking(bookingID, name, number, type);
    bookingSet.add(booking);
    }

    @Override
    public void removeBooking(int bookingID) {
    bookingSet.removeIf(booking -> booking.getBookingID() == bookingID);
    }

    @Override
    public void editBooking(int bookingID, String name, String number, String type) {
    for (Booking booking : bookingSet){
        if (booking.getBookingID() == bookingID){
            booking.setName(name);
            booking.setNumber(number);
            booking.setType(type);
    }
    }
    }

    @Override
    public HashSet<Booking> allBookings() {
        return new HashSet<>(bookingSet);
    }
}
