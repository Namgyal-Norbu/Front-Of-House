package Kitchen.Management;

// Abstract class representing booking information sent to management
public abstract class BookingsToManagement {
     int bookingID; // unique identifier
     String name; // the name of the person the booking is under
     String phoneNumber; // the telephone number provided when making a booking
     String type; // the type of booking (phone, walkIn, online, ..., etc)
     int noOfCovers; // the number of people the booking is for

    public BookingsToManagement(int bookingID, String name, String phoneNumber, String type, int noOfCovers) {
        this.bookingID = bookingID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.noOfCovers = noOfCovers;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }

    public int getNoOfCovers() {
        return noOfCovers;
    }
}