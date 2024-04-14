package Kitchen;

import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class FOHtoKitchenImpl implements FOHtoKitchen {

    private static final String DB_URL = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t28";
    private static final String DB_USERNAME = "in2033t28_d";
    private static final String DB_PASSWORD = "oExQzkCEgLw";

    @Override
    public OrdersToKitchen getPendingOrder(LocalTime time) {
        OrdersToKitchen order = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "SELECT * FROM Orders WHERE TIME(orderTime) <= ? AND isComplete = false";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, time);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int orderID = resultSet.getInt("orderID");
                String tableNoStr = resultSet.getString("tableNo");
                List<Integer> tableNo = Arrays.stream(tableNoStr.split(", "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                Map<Integer, Integer> dishes = new HashMap<>();
                List<String> allergies = new ArrayList<>();

                String query = "SELECT * FROM OrderedDishes WHERE orderID = ?";
                PreparedStatement subStatement = conn.prepareStatement(query);
                subStatement.setInt(1, orderID);
                ResultSet subResultSet = subStatement.executeQuery();
                while (subResultSet.next()) {
                    int dishID = subResultSet.getInt("dishID");
                    int quantity = subResultSet.getInt("quantity");
                    String allergens = subResultSet.getString("allergens");

                    if (!allergens.equals("N/A")) {
                        allergies.add(allergens);
                    }
                    dishes.put(dishID, dishes.getOrDefault(dishID, 0) + quantity);
                }

                order = new ConcreteOrder(orderID, tableNo, dishes, allergies, false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }


    private static class ConcreteOrder extends OrdersToKitchen {
        public ConcreteOrder(int orderID, List<Integer> tableNo, Map<Integer, Integer> dishes,
                             List<String> allergies, boolean isOrderComplete) {
            super(orderID, tableNo, dishes, allergies, isOrderComplete);
        }
    }
}

