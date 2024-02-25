package Kitchen;

import java.util.List;
import java.util.Map;

// Abstract class representing order information sent to the Kitchen
public abstract class OrdersToKitchen {
    private int orderID; // unique identifier
    private int tableNo; // the table number
    private Map<Integer, Integer> dishes; // a map of dishIDs and their quantities
    private List<String> allergies; // a list of all potential allergies in the order
    private Map<Integer, List<String>> removedIngr; // a map of a dishID with a list of unwanted ingredients by diners

    private Boolean isOrderComplete; // status checker for when order is complete

    /**
     * Constructor that initialises OrdersToKitchen
     * @param orderID
     * @param tableNo
     * @param dishes
     * @param allergies
     * @param removedIngr
     */
    public OrderToKitchen(int orderID, int tableNo, Map<Integer, Integer> dishes, 
            List<String> allergies, Map<Integer, List<String>> removedIngr) {
        this.orderID = orderID;
        this.tableNo = tableNo;
        this.dishes = dishes;
        this.allergies = allergies;
        this.removedIngr = removedIngr;
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

    /**
     * Getter method that returns isOrderComplete
     * @return isOrderComplete
     */
    public List<String> getIsOrderComplete() {return isOrderComplete;};

    /**
     * Getter method that returns removedIngr
     * @return removedIngr
     */
    public Map<Integer, List<String>> getRemovedIngr() {return removedIngr;};


    /**
     * Setter method when order is complete
     * @param status
     */
    public void setIsOrderComplete(Boolean status) {isOrderComplete = status;};

}

