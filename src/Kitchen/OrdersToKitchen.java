package Kitchen;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class OrdersToKitchen {
    private int orderID; // unique identifier
    private int tableNo; // the table number
    private Map<Integer, Integer> dishes; // a map of dishIDs and their quantities
    private List<String> allergies; // a list of all potential allergies in the order
    private Map<Integer, List<String>> removedIngr; // a map of a dishID with a list of unwanted ingredients by diners

    private Boolean isOrderComplete; // status checker for when order is complete

    /**
     * Constructor that initialises OrdersToKitchen with data fetched from the database
     * @param orderID the ID of the order to fetch
     * @param connection database connection object
     */
    public OrdersToKitchen(int orderID, Connection connection) {
        this.orderID = orderID;
        loadDataFromDatabase(orderID, connection);
    }

    private void loadDataFromDatabase(int orderID, Connection connection) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.tableNo = rs.getInt("table_no");
                this.dishes = parseDishes(rs.getString("dishes"));
                this.allergies = parseAllergies(rs.getString("allergies"));
                this.removedIngr = parseRemovedIngredients(rs.getString("removed_ingr"));
                this.isOrderComplete = rs.getBoolean("is_order_complete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sample parsers assuming JSON storage
    private Map<Integer, Integer> parseDishes(String json) {
        return new HashMap<>();
    }

    private List<String> parseAllergies(String json) {
        return null;
    }

    private Map<Integer, List<String>> parseRemovedIngredients(String json) {
        return new HashMap<>();
    }

    public int getOrderID() { return orderID; }
    public int getTableNo() { return tableNo; }
    public Map<Integer, Integer> getDishes() { return dishes; }
    public List<String> getAllergies() { return allergies; }
    public Map<Integer, List<String>> getRemovedIngr() { return removedIngr; }
    public boolean getIsOrderComplete() { return isOrderComplete; }
    public void setIsOrderComplete(Boolean status) { this.isOrderComplete = status; }
}
