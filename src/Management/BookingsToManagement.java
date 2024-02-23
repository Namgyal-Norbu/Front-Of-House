package Management;

public class Booking {
    private int bookingID;
    private String name;
    private String number;
    private String type;

    public Booking(int bookingID, String name, String number, String type) {
        this.bookingID = bookingID;
        this.name = name;
        this.number = number;
        this.type = type;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
