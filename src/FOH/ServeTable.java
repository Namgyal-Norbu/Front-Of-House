package FOH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class ServeTable extends ServeSearch{

    private static JFrame frame;
    private static JPanel panel;

    private final int bookingID;
    private final String prefix;
    private final String forename;
    private final String surname;
    private final int occupants;
    private final String tables;

    private final JTable order;
    private final JTextField allergies;
    private final JTable courseOne;
    private final JTable courseTwo;
    private final JTable courseThree;


    public ServeTable(int bookingID, String prefix, String forename, String surname, int occupants, String tables) {
        frame = new JFrame();
        panel = new JPanel(new BorderLayout());

        this.bookingID = bookingID;
        this.prefix = prefix;
        this.forename = forename;
        this.surname = surname;
        this.occupants = occupants;
        this.tables = tables;

        order = new JTable();
        allergies = new JTextField(20);
        courseOne = new JTable();
        courseTwo = new JTable();
        courseThree = new JTable();
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setText();
        loadButtons();

        frame.setTitle("FOH Service Software");
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public void setText() {
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        textPanel.setBackground(new Color(43, 51, 54));
        JLabel text = new JLabel(
                "<html>" +
                        "Name: " + prefix + " " + forename + " " + surname + "<br>" +
                        "Booking ID: " + bookingID + "<br>" +
                        "Table(s): " + tables + "<br>" +
                        "Diners: " + occupants + "<br>" +
                        "</div>" +
                        "</html>");

        text.setFont(new Font("Arial", Font.BOLD, 15));
        text.setForeground(new Color(200, 200, 200));
        text.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));
        textPanel.add(text);

        loadActionButton(textPanel);
        seOrdersTable(textPanel);
        panel.add(textPanel);
    }


    public void loadActionButton(JPanel p) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 20));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton first = new JButton("1st Course");
        JButton second = new JButton("2nd Course");
        JButton third = new JButton("3rd Course");

        first.addActionListener(e -> {
            if (e.getSource() == first) {
                System.out.println("[event]: 1st course button clicked");
                openCourseTab("Course 1", courseOne);
            }
        });

        second.addActionListener(e -> {
            if (e.getSource() == second) {
                System.out.println("[event]: 2nd course button clicked");
                openCourseTab("Course 2", courseTwo);
            }
        });

        third.addActionListener(e -> {
            if (e.getSource() == third) {
                System.out.println("[event]: 3rd course button clicked");
                openCourseTab("Course 3", courseThree);
            }
        });

        buttonPanel.add(first);
        buttonPanel.add(second);
        buttonPanel.add(third);
        p.add(buttonPanel);
    }


    public void loadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton submit = new JButton("submit");
        submit.setPreferredSize(new Dimension(125, 40));
        submit.addActionListener(e -> {
            if (e.getSource() == submit) {
                System.out.println("[event]: submit button clicked");
                submitOrder();
            }
        });

        JButton remove = new JButton("remove");
        remove.setPreferredSize(new Dimension(125, 40));
        remove.addActionListener(e -> {
            if (e.getSource() == remove) {
                System.out.println("[event]: remove button clicked");
                removeFromOrder();
            }
        });

        JButton cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(125, 40));
        cancel.addActionListener(e -> {
            if (e.getSource() == cancel) {
                Home home;
                try {
                    frame.dispose();
                    System.out.println("[event]: cancel button clicked");
                    home = new Home();
                    home.start();

                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(remove);
        buttonPanel.add(submit);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void seOrdersTable(JPanel p) {
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        tablePanel.setBackground(new Color(43, 51, 54));

        JScrollPane scrollPane = new JScrollPane(order);
        scrollPane.setPreferredSize(new Dimension(800, 350));

        String[] columns = {
                "ID",
                "Item",
                "Allergens",
                "Price",
        };

        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columns);
        order.setModel(model);
        order.setShowGrid(true);
        order.setGridColor(Color.LIGHT_GRAY);

        tablePanel.add(scrollPane);
        p.add(tablePanel);
    }

    private void openCourseTab(String course, JTable courseTable) {
        JFrame courseFrame = new JFrame(course);
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        coursePanel.setBackground(new Color(43, 51, 54));

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        tablePanel.setBackground(new Color(43, 51, 54));

        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setPreferredSize(new Dimension(600, 350));

        String[] columns = {
                "ID",
                "Item",
                "Price"
        };
        Object[][] data = {
                {"1", "Burger", "£7.49"},
                {"2", "Steak", "£17.99"},
                {"3", "Risotto", "£11.99"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        courseTable.setModel(model);
        courseTable.setShowGrid(true);
        courseTable.setGridColor(Color.LIGHT_GRAY);

        TableColumn priceColumn = courseTable.getColumnModel().getColumn(1);
        priceColumn.setPreferredWidth(20);


        JPanel allergyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        allergyPanel.setBackground(new Color(43, 51, 54));

        JLabel text = new JLabel("Allergens:");
        text.setForeground(Color.WHITE);
        allergyPanel.add(text);
        allergyPanel.add(allergies);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton add = new JButton("Add");
        add.setPreferredSize(new Dimension(125, 40));
        add.addActionListener(e -> {
            if (e.getSource() == add) {
                System.out.println("[event]: add button clicked");
                addToOrder(courseTable, allergies);
                allergies.setText("");
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        tablePanel.add(scrollPane);
        buttonPanel.add(add, BorderLayout.SOUTH);
        buttonPanel.add(allergyPanel, BorderLayout.SOUTH);

        coursePanel.add(tablePanel);
        coursePanel.add(buttonPanel, BorderLayout.SOUTH);

        courseFrame.add(coursePanel);
        courseFrame.setSize(700, 500);
        courseFrame.setLocationRelativeTo(null);
        courseFrame.setVisible(true);
    }

    private void addToOrder(JTable courseTable, JTextField allergiesField) {
        DefaultTableModel orderModel = (DefaultTableModel) order.getModel();
        DefaultTableModel courseModel = (DefaultTableModel) courseTable.getModel();

        int[] selectedRows = courseTable.getSelectedRows();

        for (int row : selectedRows) {
            Object[] rowData = new Object[courseModel.getColumnCount() + 1];
            for (int col = 0; col < courseModel.getColumnCount() - 1; col++) {
                rowData[col] = courseModel.getValueAt(row, col);
            }
            rowData[courseModel.getColumnCount() - 1] = allergiesField.getText();
            rowData[courseModel.getColumnCount()] = courseModel.getValueAt(row, courseModel.getColumnCount() - 1);
            orderModel.addRow(rowData);
        }
    }

    private void removeFromOrder() {
        DefaultTableModel orderModel = (DefaultTableModel) order.getModel();
        int[] selectedRows = order.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            orderModel.removeRow(selectedRows[i]);
        }
    }

    private void submitOrder() {
        DefaultTableModel orderModel = (DefaultTableModel) order.getModel();
        Map<String, String[]> orderedDishes = new HashMap<>(); // Using a string key combining dishID and allergies

        // Iterate through each row in the order table
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            int dishID = Integer.parseInt((String) orderModel.getValueAt(i, 0)); // Assuming the dish ID is in the first column
            String dishName = (String) orderModel.getValueAt(i, 1); // Assuming the dish name is in the second column
            String allergies = (String) orderModel.getValueAt(i, 2); // Assuming the allergies are in the third column

            if (Objects.equals(allergies, "") || allergies.isEmpty()) {
                allergies = "N/A";
            }

            // Generate a unique key for each combination of dish ID and allergies
            String key = dishID + "-" + allergies;

            // If the dish is already in the map, update its quantity
            if (orderedDishes.containsKey(key)) {
                String[] dishData = orderedDishes.get(key);
                dishData[2] = String.valueOf(Integer.parseInt(dishData[2]) + 1); // Increment quantity
            } else {
                // If the dish is not in the map, add it with initial quantity 1
                String[] dishData = {dishName, allergies, "1"};
                orderedDishes.put(key, dishData);
            }
        }

        insertOrder(orderedDishes);
    }

    public void insertOrder(Map<String, String[]> orderedDishes) {
        try {
            Connection conn = JDBC.getConn();

            // Insert into Orders table
            String insertOrderSQL = "INSERT INTO Orders (bookingID, tableNos, isComplete) VALUES (?, ?, ?)";
            PreparedStatement orderStatement = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            orderStatement.setInt(1, bookingID);
            orderStatement.setString(2, tables);
            orderStatement.setBoolean(3, false); // Assuming isComplete is initially set to false
            orderStatement.executeUpdate();

            int orderID;
            try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

            // Insert into OrderedDishes table
            String insertOrderedDishesSQL = "INSERT INTO OrderedDishes (orderID, dishID, dishName, allergens, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement orderedDishesStatement = conn.prepareStatement(insertOrderedDishesSQL);
            for (Map.Entry<String, String[]> entry : orderedDishes.entrySet()) {
                String key = entry.getKey();
                String[] dishData = entry.getValue();

                // Split the key to extract the dish ID
                String[] parts = key.split("-");
                int dishID = Integer.parseInt(parts[0]);

                String dishName = dishData[0];
                String allergies = dishData[1];
                int quantity = Integer.parseInt(dishData[2]);

                orderedDishesStatement.setInt(1, orderID);
                orderedDishesStatement.setInt(2, dishID);
                orderedDishesStatement.setString(3, dishName);
                orderedDishesStatement.setString(4, allergies);
                orderedDishesStatement.setInt(5, quantity);
                orderedDishesStatement.executeUpdate();
            }

            System.out.println("Order successfully inserted.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}