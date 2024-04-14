package Kitchen.Management;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class FOHtoManagementImpl implements FOHtoManagement {

    private static final String DB_URL = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t28";
    private static final String DB_USERNAME = "in2033t28_d";
    private static final String DB_PASSWORD = "oExQzkCEgLw";


    @Override
    public HashSet<BookingsToManagement> getBookings(LocalDate day) {
        HashSet<BookingsToManagement> bookings = new HashSet<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "SELECT * FROM Bookings WHERE date = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(day));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int bookingID = resultSet.getInt("bookingID");
                String forename = resultSet.getString("forename");
                String surname = resultSet.getString("surname");
                String name = forename + " " + surname;
                String phoneNumber = resultSet.getString("telephone");

                boolean isWalkIn = resultSet.getBoolean("isWalkIn");
                String type = isWalkIn ? "Walk-In" : "Telephone";

                int noOfCovers = resultSet.getInt("occupants");

                BookingsToManagement booking = new ConcreteBooking(bookingID, name, phoneNumber, type, noOfCovers);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookings;
    }

    @Override
    public HashSet<SalesToManagement> getSales(LocalDate day) {
        HashSet<SalesToManagement> sales = new HashSet<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "SELECT * FROM Sales WHERE date = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(day));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int saleID = resultSet.getInt("saleID");
                boolean nhsDisc = resultSet.getBoolean("nhsDisc");
                boolean armyDisc = resultSet.getBoolean("armyDisc");
                boolean optionalCharge = resultSet.getBoolean("serviceCharge");
                double total = resultSet.getDouble("total");
                String paymentMethod = resultSet.getString("paymentMethod");

                String dishListString = resultSet.getString("dishList");
                String[] dishIDs = dishListString.split(", ");
                ArrayList<Integer> dishList = new ArrayList<>();
                for (String dishID : dishIDs) {
                    dishList.add(Integer.parseInt(dishID));
                }

                SalesToManagement sale = new ConcreteSales(saleID, nhsDisc, armyDisc, optionalCharge, total, paymentMethod, dishList);
                sales.add(sale);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sales;
    }


    private static class ConcreteBooking extends BookingsToManagement {
        public ConcreteBooking(int bookingID, String name, String phoneNumber, String type, int noOfCovers) {
            super(bookingID, name, phoneNumber, type, noOfCovers);
        }
    }

    public static class ConcreteSales extends SalesToManagement {
        public ConcreteSales(int saleID, boolean nhsDisc, boolean armyDisc, boolean optionalCharge,
                                         double total, String paymentMethod, ArrayList<Integer> dishList) {
            super(saleID, nhsDisc, armyDisc, optionalCharge, total, paymentMethod, dishList);
        }
    }
}
