package Management;

import java.time.LocalDate;
import java.util.HashSet;

// Interface defining communication from FOH to management
public interface FOHtoManagement {
    /**
     * Method that gets booking information, and returns it as an object to Management
     * @param date (to get all bookings made in that day)
     * @return HashSet of BookingstoManagement class objects
     */
    HashSet<BookingstoManagement> getBookings(LocalDate day);
    
    /**
     * Method that gets sales information, and returns it as an object to Management
     * @param date (to get all sales made in that day)
     * @return HashSet of SalestoManagement class objects
     */
    HashSet<SalesToManagement> getSales(LocalDate day);
}

