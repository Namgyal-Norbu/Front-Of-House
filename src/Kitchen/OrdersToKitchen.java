package Kitchen;

import java.util.List;
import java.util.Map;

// Abstract class representing order information sent to the Kitchen
public abstract class OrdersToKitchen {
    private int orderID; // unique identifier
    private int tableNo; // the table number
    private Map<Integer, Integer> dishes; // a map of dishIDs and their quantities
    private List<String> allergies; // a list of all potential allergies in the order

    /**
     * Constructor that initialises OrdersToKitchen
     * @param orderID
     * @param tableNo
     * @param dishes
     * @param allergies
     */
    public OrderToKitchen(int orderID, int tableNo, Map<Integer, Integer> dishes, List<String> allergies) {
        this.orderID = orderID;
        this.tableNo = tableNo;
        this.dishes = dishes;
        this.allergies = allergies;
    }
    
     /**
     * Getter method that returns orderID
     * @return orderID
     */   
    public int getOrderID() {return orderID;};
    
    /**
     * Getter method that returns tableNo
     * @return tableNo
     */
    public int getTableNo() {return tableNo;};
    
    /**
     * Getter method that returns dishes
     * @return dishes
     */
    public Map<Integer, Integer> getDishes() {return dishes;};
    
    /**
     * Getter method that returns allergies
     * @return allergies
     */
    public List<String> getAllergies() {return allergies;};
}

