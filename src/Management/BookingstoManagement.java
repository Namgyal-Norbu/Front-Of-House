package Management;

// Abstract class representing booking information sent to management
public abstract class BookingstoManagement {
     private int bookingID; // unique identifier
     private String name; // the name of the person the booking is under
     private String number; // the telephone number provided when making a booking 
     private String type; // the type of booking (phone, walkin, online, ..., etc)

     /**
     * Constructor to initialise the BookingsToManagement
     * @param bookingID 
     * @param name
     * @param number
     * @param type
     */
    public BookingstoManagement(int bookingID, String name, String number, String type) {
        this.bookingID = bookingID;
        this.name = name;
        this.number = number;
        this.type = type;
    }

    /**
     * Getter method that returns bookingID
     * @return bookingID
     */
    public int getBookingID() { return bookingID; }

    /**
     * Getter method that returns name
     * @return name
     */
    public String getName() { return name; }
    
    /**
     * Getter method that returns number
     * @return number
     */   
    public String getNumber() { return number; }
    
    /**
     * Getter method that returns type
     * @return type
     */  
    public String getType() { return type; }
}

