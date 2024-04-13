package Kitchen;
import java.sql.*;
import java.time.LocalTime;

// Interface defining communication from FOH to management
public interface FOHtoKitchen {

    /**
     * Method that gets order information, and returns it as an object to the Kitchen
     * @param time (specify a time to reciece the latest order made then)
     * @return OrdersToKitchen class object
     */
    OrdersToKitchen getPendingOrder(LocalTime time);

}

