package Management;

public abstract class BookingstoManagement {
     int bookingID;
     String name;
     String number;
     String type;

    public BookingstoManagement(int bookingID, String name, String number, String type) {
        this.bookingID = bookingID;
        this.name = name;
        this.number = number;
        this.type = type;
    }

    public int getBookingID() {
        return bookingID;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getType() {
        return type;
    }

}

