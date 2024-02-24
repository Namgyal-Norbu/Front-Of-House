package Management;

import java.util.HashSet;

// Interface defining communication from FOH to management
public interface FOHtoManagement {
    /**
     * Method that gets booking information, and returns it as an object to Management
     * @param bookings
     * @return HashSet of BookingstoManagement class objects
     */
    HashSet<BookingstoManagement> getBookings(HashSet<BookingstoManagement> bookings);
    
    /**
     * Method that gets sales information, and returns it as an object to Management
     * @param sales
     * @return HashSet of SalestoManagement class objects
     */
    HashSet<SalesToManagement> getSales(HashSet<SalesToManagement> sales);
}

