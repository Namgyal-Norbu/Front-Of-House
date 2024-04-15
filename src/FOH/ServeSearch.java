package FOH;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * The ServeSearch class provides a user interface for searching for existing bookings
 * based on entered booking details. This class facilitates finding and selecting a booking to serve.
 */

public class ServeSearch {

    private static JFrame frame;
    private static JPanel panel;
    private final JLabel title;

    private final JTextField forename;
    private final JTextField telephone;
    private final JDatePickerImpl date;

    /**
     * Constructs a new ServeSearch instance. Initializes GUI components for inputting booking details.
     */

    public ServeSearch() {
        frame = new JFrame();
        panel = new JPanel(new BorderLayout());
        title = new JLabel("Enter Booking Details");

        forename = new JTextField(15);
        telephone = new JTextField(15);

        UtilDateModel model = new UtilDateModel();
        date = new JDatePickerImpl(new JDatePanelImpl(model));
    }

    /**
     * Initialises and displays the booking search interface.
     * Sets up the form for entering booking details and submit/cancel buttons.
     * 
     * @throws IOException if an I/O error occurs during the setup.
     */

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        loadButtons();
        setTitle();
        setForm();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Sets the title properties and adds it to the north region of the main panel.
     */

    public void setTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(200, 200, 200));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);
    }

    /**
     * Configures and adds a form with fields for entering booking details to the panel.
     */

    public void setForm() {
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 11, 30));
        namePanel.setBackground(new Color(43, 51, 54));
        JLabel nameLabel = new JLabel("Forename:");
        nameLabel.setForeground(Color.WHITE);
        namePanel.add(nameLabel);
        namePanel.add(forename);

        JPanel telephonePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
        telephonePanel.setBackground(new Color(43, 51, 54));
        JLabel telephoneLabel = new JLabel("Telephone:");
        telephoneLabel.setForeground(Color.WHITE);
        telephonePanel.add(telephoneLabel);
        telephonePanel.add(telephone);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        datePanel.setBackground(new Color(43, 51, 54));
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(Color.WHITE);
        datePanel.add(dateLabel);
        datePanel.add(date);

        namePanel.add(telephonePanel);
        namePanel.add(datePanel);
        panel.add(namePanel);
    }

    /**
     * Configures and adds buttons for submitting or canceling the search to the panel.
     */

    public void loadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton submit = new JButton("submit");
        submit.setPreferredSize(new Dimension(125, 40));
        submit.addActionListener(e -> {
            if (e.getSource() == submit) {
                frame.dispose();
                System.out.println("[event]: submit button clicked");
                String forenameText = forename.getText();
                String telephoneText = telephone.getText();
                Date selectedDate = (Date) date.getModel().getValue();

                searchBooking(forenameText, telephoneText, selectedDate);
            }

        });

        JButton cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(125, 40));
        cancel.addActionListener(e -> {
            if (e.getSource() == cancel) {
                frame.dispose();
                Home home;
                try {
                    home = new Home();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    System.out.println("[event]: cancel button clicked");
                    home.start();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(submit);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

     /**
     * Searches for a booking in the database based on the provided details and displays the result.
     * 
     * @param forenameText The forename associated with the booking.
     * @param telephoneText The telephone number associated with the booking.
     * @param selectedDate The date of the booking.
     */

    public void searchBooking(String forenameText, String telephoneText, Date selectedDate) {

        try {
            Connection conn = JDBC.getConn();

            String sql = "SELECT * FROM Bookings WHERE forename = ? AND telephone = ? AND date = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, forenameText);
            statement.setString(2, telephoneText);
            statement.setDate(3, new java.sql.Date(selectedDate.getTime()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int bookingID = resultSet.getInt("bookingID");
                String prefix = resultSet.getString("prefix");
                String forename = resultSet.getString("forename");
                String surname = resultSet.getString("surname");
                int occupants = resultSet.getInt("occupants");

                String tables = searchBookedTables(conn, bookingID);

                try {
                    ServeTable serve = new ServeTable(bookingID, prefix, forename, surname, occupants, tables);
                    serve.start();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                System.out.println("No booking found with the given details.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String searchBookedTables(Connection conn, int ID) throws SQLException {

        StringBuilder tables = new StringBuilder();
        String sql = "SELECT tableID FROM BookedTables WHERE bookingID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int tableID = resultSet.getInt("tableID");
            tables.append(tableID).append(", ");
        }

        if (!tables.isEmpty()) {
            tables.setLength(tables.length() - 2);
        }

        return tables.toString();
    }
}
