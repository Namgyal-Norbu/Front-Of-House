package Management;

// Abstract class representing booking information sent to management
public abstract class BookingstoManagement {
     private int bookingID; // unique identifier
     private String name; // the name of the person the booking is under
     private String phoneNumber; // the telephone number provided when making a booking 
     private String type; // the type of booking (phone, walkin, online, ..., etc)
     private int noOfCovers; // the number of people the booking is for

     /**
     * Constructor to initialise the BookingsToManagement
     * @param bookingID 
     * @param name
     * @param phoneNumber
     * @param type
     * @param noOfCovers
     */
    public BookingstoManagement(int bookingID, String name, String phoneNumber, String type, int noOfCovers) {
        this.bookingID = bookingID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.noOfCovers = noOfCovers;
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
    public String getPhoneNumber() { return phoneNumber; }
    
    /**
     * Getter method that returns type
     * @return type
     */  
    public String getType() { return type; }

    /**
     * Getter method that returns noOfCovers
     * @return noOfCovers
     */  
    public String getNoOfCovers() { return noOfCovers; }

}
