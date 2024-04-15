package Kitchen;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class OrdersToKitchen {
    private final int orderID; // unique identifier
    private final List<Integer> tableNo; // the table number
    private final Map<Integer, Integer> dishes; // a map of dishIDs and their quantities
    private final List<String> allergies; // a list of all potential allergies in the order
    private Boolean isOrderComplete; // status checker for when order is complete

    public OrdersToKitchen(int orderID, List<Integer> tableNo, Map<Integer, Integer> dishes,
                           List<String> allergies, boolean isOrderComplete) {
        this.orderID = orderID;
        this.tableNo = tableNo;
        this.dishes = dishes;
        this.allergies = allergies;
        this.isOrderComplete = isOrderComplete;
    }

    public int getOrderID() { return orderID; }
    public List<Integer> getTableNo() { return tableNo; }
    public Map<Integer, Integer> getDishes() { return dishes; }
    public List<String> getAllergies() {
        allergies.removeAll(Collections.singleton("N/A"));
        return allergies;
    }
    public boolean getIsOrderComplete() { return isOrderComplete; }
    public void setIsOrderComplete(Boolean status) { this.isOrderComplete = status; }
}
