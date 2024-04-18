package FOH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Checkout class manages the user interface for finalizing orders and processing payments
 * within the FOH Service Software. It allows applying discounts, service charges, selecting payment methods,
 * and recording sales details.
 */
public class Checkout {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel title;

    private final int bookingID;
    private final JTable summary;
    private final JRadioButton nhsDisc;
    private final JRadioButton armyDisc;
    private final JRadioButton serviceCharge;
    private final JComboBox<String> paymentMethod;
    private final JTextArea total;
    private double totalPrice = 0.0;


    /**
     * Constructs a new Checkout instance. Initializes the main frame, panel, and title label.
     *
     * @param bookingID The ID of the booking associated with the checkout.
     */
    public Checkout(int bookingID) {
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Checkout");

        this.bookingID = bookingID;

        nhsDisc = new JRadioButton("NHS Discount");
        armyDisc = new JRadioButton("Army Discount");
        serviceCharge = new JRadioButton("Service Charge");

        summary = new JTable();
        String[] paymentSources = {"card", "cash"};
        paymentMethod = new JComboBox<>(paymentSources);
        total = new JTextArea();
    }

    /**
     * Initializes and displays the checkout interface.
     * This method sets up the background, layout, and buttons for the panel, and
     * makes the frame visible to the user.
     *
     * @throws IOException if an I/O error occurs when setting up the page.
     */
    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        setTitle();
        loadButtons();
        setPage();
        searchOrder();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(550, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Sets up the title for the checkout interface.
     * Configures the font, color, alignment, and border of the title label.
     */
    public void setTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(200, 200, 200));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);
    }

    /**
     * Sets up the main content of the checkout page, including summary table,
     * discount options, payment method selection, and total amount.
     */
    public void setPage() {
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 35, 20));
        summaryPanel.setBackground(new Color(43, 51, 54));

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        radioPanel.setBackground(new Color(43, 51, 54));

        JPanel radioPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        radioPanel2.setBackground(new Color(43, 51, 54));

        nhsDisc.setForeground(new Color(200, 200, 200));
        armyDisc.setForeground(new Color(200, 200, 200));
        serviceCharge.setForeground(new Color(200, 200, 200));

        nhsDisc.addActionListener(e -> {
            if (e.getSource() == nhsDisc) {
                System.out.println("[event]: NHS discount button clicked");
                if (nhsDisc.isSelected()) {
                    applyDiscount(0.12); // Applying 12% discount

                } else {
                    applyDiscount(-0.12); // Removing 12% discount
                }
            }
        });

        armyDisc.addActionListener(e -> {
            if (e.getSource() == armyDisc) {
                System.out.println("[event]: army discount button clicked");
                if (armyDisc.isSelected()) {
                    applyDiscount(0.10); // Applying 10% discount

                } else {
                    applyDiscount(-0.10); // Removing 10% discount
                }
            }
        });

        serviceCharge.addActionListener(e -> {
            if (e.getSource() == serviceCharge) {
                System.out.println("[event]: service charge button clicked");
                if (serviceCharge.isSelected()) {
                    applyCharge(0.18); // Adding 18% service charge

                } else {
                    applyCharge(-0.18); // Removing 18% service charge
                }
            }
        });
        radioPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        radioPanel.add(serviceCharge, BorderLayout.NORTH);
        radioPanel.add(nhsDisc, BorderLayout.CENTER);
        radioPanel.add(armyDisc, BorderLayout.CENTER);

        JPanel payPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        payPanel.setBackground(new Color(43, 51, 54));
        JLabel payLabel = new JLabel("Payment Method:");
        payLabel.setForeground(new Color(200, 200, 200));
        payPanel.add(payLabel);
        payPanel.add(paymentMethod);
        payPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        totalPanel.setBackground(new Color(43, 51, 54));
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setForeground(new Color(200, 200, 200));
        total.setEditable(false);
        total.setPreferredSize(new Dimension(80, 20));
        totalPanel.add(totalLabel);
        totalPanel.add(total);
        totalPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        setSummaryTable(summaryPanel);
        summaryPanel.add(radioPanel);
        summaryPanel.add(payPanel);
        summaryPanel.add(totalPanel);
        panel.add(summaryPanel, BorderLayout.CENTER);
    }

    /**
     * Sets up the summary table with details of ordered dishes.
     *
     * @param p The panel to which the summary table is added.
     */
    public void setSummaryTable(JPanel p) {
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.setBackground(new Color(43, 51, 54));

        JScrollPane scrollPane = new JScrollPane(summary);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        String[] columns = {
                "Item",
                "Quantity",
                "Price"
        };

        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columns);
        summary.setModel(model);
        summary.setShowGrid(true);
        summary.setGridColor(Color.LIGHT_GRAY);
        summary.setRowHeight(20);

        tablePanel.add(scrollPane);
        p.add(tablePanel) ;
    }

    /**
     * Configures and adds pay and cancel buttons to the checkout interface.
     */
    public void loadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton pay = new JButton("pay");
        pay.setPreferredSize(new Dimension(125, 40));
        pay.addActionListener(e -> {
            if (e.getSource() == pay) {
                frame.dispose();
                System.out.println("[event]: pay button clicked");
                insertSale();

                try {
                    Home home = new Home();
                    home.start();

                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        JButton cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(125, 40));
        cancel.addActionListener(e -> {
            if (e.getSource() == cancel) {
                frame.dispose();
                ViewReservation view;
                try {
                    frame.dispose();
                    System.out.println("[event]: cancel button clicked");
                    view = new ViewReservation();
                    view.start();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(pay);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Searches for the order associated with the booking ID and populates the summary table.
     */
    public void searchOrder() {
        try {
            Connection conn = JDBC.getConn();

            // SQL query to retrieve dishes, quantities, and prices associated with the bookingID
            String query = "SELECT dishName, quantity, price FROM OrderedDishes WHERE orderID IN (SELECT orderID FROM Orders WHERE bookingID = ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, bookingID);
            ResultSet resultSet = statement.executeQuery();

            // Create table model for summary JTable
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Dish Name");
            model.addColumn("Quantity");
            model.addColumn("Price");

            while (resultSet.next()) {
                String dishName = resultSet.getString("dishName");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                totalPrice += price;
                model.addRow(new Object[]{dishName, quantity, "£" + price});
            }

            // Set the table model to the summary JTable
            summary.setModel(model);
            DecimalFormat df = new DecimalFormat("#.##");
            totalPrice = Double.parseDouble(df.format(totalPrice));
            total.setText("£" + totalPrice);

        } catch (SQLException e) {
            throw new RuntimeException("Error while searching order: " + e.getMessage());
        }
    }

    /**
     * Applies a discount to the total price based on the selected discount option.
     *
     * @param discountPercentage The percentage of discount to apply.
     */
    private void applyDiscount(double discountPercentage) {
        totalPrice -= totalPrice * discountPercentage;
        totalPrice = Math.round(totalPrice * 100.0) / 100.0; // Round to two decimal places
        total.setText("£" + String.format("%.2f", totalPrice));
    }

    /**
     * Applies a service charge to the total price based on the selected service charge option.
     *
     * @param chargePercentage The percentage of service charge to apply.
     */
    private void applyCharge(double chargePercentage) {
        totalPrice += totalPrice * chargePercentage;
        totalPrice = Math.round(totalPrice * 100.0) / 100.0; // Round to two decimal places
        total.setText("£" + String.format("%.2f", totalPrice));
    }

    /**
     * Inserts the sale data into the database, including order details, discounts, payment method, and total amount.
     */
    public void insertSale() {
        try {
            Connection conn = JDBC.getConn();
            String dishList = getDishList();
            boolean nhsDiscount = nhsDisc.isSelected();
            boolean armyDiscount = armyDisc.isSelected();
            boolean serviceChargeApplied = serviceCharge.isSelected();
            String paymentMethodValue = (String) paymentMethod.getSelectedItem();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            String queryOrderID = "SELECT orderID FROM Orders WHERE bookingID = ?";
            PreparedStatement statementOrderID = conn.prepareStatement(queryOrderID);
            statementOrderID.setInt(1, bookingID);
            ResultSet resultSetOrderID = statementOrderID.executeQuery();

            int orderID = -1;
            if (resultSetOrderID.next()) {
                orderID = resultSetOrderID.getInt("orderID");
            }

            // SQL query to insert sale data into Sales table
            String query = "INSERT INTO Sales (orderID, dishList, nhsDisc, armyDisc, serviceCharge, paymentMethod, total, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderID);
            statement.setString(2, dishList);
            statement.setBoolean(3, nhsDiscount);
            statement.setBoolean(4, armyDiscount);
            statement.setBoolean(5, serviceChargeApplied);
            statement.setString(6, paymentMethodValue);
            statement.setDouble(7, totalPrice);
            statement.setString(8, date);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                int saleID = -1;
                if (generatedKeys.next()) {
                    saleID = generatedKeys.getInt(1);
                    System.out.println("Sale ID: " + saleID);
                }
                System.out.println("Sale data inserted successfully.");

                // Update booking to mark it as finished
                String updateBookingQuery = "UPDATE Bookings SET isFinished = true WHERE bookingID = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateBookingQuery);
                updateStatement.setInt(1, bookingID);
                int updatedRows = updateStatement.executeUpdate();
                if (updatedRows > 0) {
                    System.out.println("Booking marked as finished.");
                } else {
                    System.out.println("Failed to mark booking as finished.");
                }
            } else {
                System.out.println("Failed to insert sale data.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting sale data: " + e.getMessage());
        }
    }

    /**
     * Retrieves the list of dish IDs associated with the order.
     *
     * @return A string containing the list of dish IDs.
     */
    private String getDishList() {
        ArrayList<String> dishIDs = new ArrayList<>();

        try {
            Connection conn = JDBC.getConn();
            String queryOrderID = "SELECT orderID FROM Orders WHERE bookingID = ?";
            PreparedStatement statementOrderID = conn.prepareStatement(queryOrderID);
            statementOrderID.setInt(1, bookingID);
            ResultSet resultSetOrderID = statementOrderID.executeQuery();

            if (resultSetOrderID.next()) {
                int orderID = resultSetOrderID.getInt("orderID");

                String queryDishes = "SELECT dishID FROM OrderedDishes WHERE orderID = ?";
                PreparedStatement statementDishes = conn.prepareStatement(queryDishes);
                statementDishes.setInt(1, orderID);
                ResultSet resultSetDishes = statementDishes.executeQuery();

                while (resultSetDishes.next()) {
                    int dishID = resultSetDishes.getInt("dishID");
                    dishIDs.add(String.valueOf(dishID));
                }
            } else {
                throw new RuntimeException("No order found for booking ID: " + bookingID);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving dish list: " + e.getMessage());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String dishID : dishIDs) {
            if (!stringBuilder.isEmpty()) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(dishID);
        }
        return stringBuilder.toString();
    }
}
