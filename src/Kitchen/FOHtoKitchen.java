package Kitchen;
import java.sql.*;
import java.time.LocalTime;

// Interface defining communication from FOH to management
public interface FOHtoKitchen {
    OrdersToKitchen getPendingOrder(LocalTime time);
}
