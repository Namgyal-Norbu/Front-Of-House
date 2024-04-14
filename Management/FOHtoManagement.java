package Kitchen.Management;

import java.time.LocalDate;
import java.util.HashSet;

// Interface defining communication from FOH to management
public interface FOHtoManagement {
    HashSet<BookingsToManagement> getBookings(LocalDate day);

    HashSet<SalesToManagement> getSales(LocalDate day);
}
