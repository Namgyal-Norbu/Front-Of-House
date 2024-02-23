package Kitchen;

import java.util.List;
import java.util.Map;

public abstract class OrderToKitchen {
    int orderID;
    int tableNo;
    Map<Integer, Integer> dishes;
    List<String> allergies;

    public OrderToKitchen(int orderID, int tableNo, Map<Integer, Integer> dishes, List<String> allergies) {
        this.orderID = orderID;
        this.tableNo = tableNo;
        this.dishes = dishes;
        this.allergies = allergies;
    }
    
    public int getOrderID() {return orderID;};
    public int getTableNo() {return tableNo;};
    public Map<Integer, Integer> getDishes() {return dishes;};
    public List<String> getAllergies() {return allergies;};
}

