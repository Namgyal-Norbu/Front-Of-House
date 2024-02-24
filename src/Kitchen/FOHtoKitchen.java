package Kitchen;

// Interface defining communication from FOH to management
public interface FOHtoKitchen {
    /**
     * Method that gets order information, and returns it as an object to the Kitchen
     * @param order
     * @return OrdersToKitchen class object
     */
    OrdersToKitchen getPendingOrder(OrdersToKitchen order);
}

