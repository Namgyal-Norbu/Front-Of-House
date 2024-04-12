package FOH;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class EditReservation {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel title;

    private  final int bookingID;
    private final JCheckBox isWalkIn;
    private final JComboBox<String> prefix;
    private final JTextField forename;
    private final JTextField surname;
    private final JTextField telephone;
    private final JDatePickerImpl date;
    private final JComboBox<String> time;
    private final JTextField occupants;
    private final JButton tableNo;

    public EditReservation(int selectedBookingID, boolean isWalkInSelected, String selectedPrefix, String selectedForename,
                           String selectedSurname, String selectedTelephone, UtilDateModel selectedDateModel,
                           String selectedTime, Integer selectedOccupants, String selectedTableNo) {

        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Edit Reservation");

        bookingID = selectedBookingID;

        isWalkIn = new JCheckBox();
        isWalkIn.setSelected(isWalkInSelected);

        String[] prefixList = {"Mr.", "Ms.", "Mrs.", "Dr."};
        prefix = new JComboBox<>(prefixList);
        prefix.setSelectedItem(selectedPrefix);

        forename = new JTextField(20);
        forename.setText(selectedForename);

        surname = new JTextField(20);
        surname.setText(selectedSurname);

        telephone = new JTextField(20);
        telephone.setText(selectedTelephone);

        occupants = new JTextField(3);
        occupants.setText(selectedOccupants.toString());

        UtilDateModel model = new UtilDateModel(selectedDateModel.getValue());
        date = new JDatePickerImpl(new JDatePanelImpl(model));

        selectedTime = selectedTime.substring(0, selectedTime.length() - 3);
        time = new JComboBox<>(new TimeModel(selectedTime));

        tableNo = new JButton(selectedTableNo);
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        setTitle();
        loadButtons();
        setForm();

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("FOH Service Software");
        frame.setSize(550, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(200, 200, 200));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);
    }

    public void addField(JPanel panel, FlowLayout layout ,String label, JTextField field) {
        JPanel fieldPanel = new JPanel(layout);
        fieldPanel.setBackground(new Color(43, 51, 54));

        JLabel text = new JLabel(label);
        text.setForeground(Color.WHITE);

        fieldPanel.add(text);
        fieldPanel.add(field);
        panel.add(fieldPanel);
    }

    public void addDropDown(JPanel panel, FlowLayout layout ,String label, JComboBox<String> dropBox) {
        JPanel fieldPanel = new JPanel(layout);
        fieldPanel.setBackground(new Color(43, 51, 54));

        JLabel text = new JLabel(label);
        text.setForeground(Color.WHITE);

        fieldPanel.add(text);
        fieldPanel.add(dropBox);
        panel.add(fieldPanel);
    }

    public void setForm() {
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        formPanel.setBackground(new Color(43, 51, 54));

        JPanel boxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        boxPanel.setBackground(new Color(43, 51, 54));
        JLabel text = new JLabel("Walk-In:");
        text.setForeground(Color.WHITE);
        boxPanel.add(text);
        boxPanel.add(isWalkIn);
        formPanel.add(boxPanel);

        // prefix drop-down list
        addDropDown(formPanel, new FlowLayout(FlowLayout.LEFT, 15, 5), "Prefix:", prefix);

        // forename text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 11, 5), "Forename:", forename);

        // surname text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 18, 5), "Surname:", surname);

        // telephone text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 8, 5), "Telephone:", telephone);

        // calendar date selector
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        datePanel.setBackground(new Color(43, 51, 54));
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(Color.WHITE);
        datePanel.add(dateLabel);
        datePanel.add(date);

        formPanel.add(datePanel);

        // time of day selector
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        timePanel.setBackground(new Color(43, 51, 54));
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setForeground(Color.WHITE);
        timePanel.add(timeLabel);
        timePanel.add(time);

        formPanel.add(timePanel);

        // occupants text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 10, 20), "Occupants:", occupants);

        // table No field
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        tablePanel.setBackground(new Color(43, 51, 54));
        JLabel tableLabel = new JLabel("Table No:");
        tableLabel.setForeground(Color.WHITE);

        tablePanel.add(tableLabel);
        tablePanel.add(tableNo);
        formPanel.add(tablePanel);

        panel.add(formPanel, BorderLayout.CENTER);
    }

    public void loadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton submit = new JButton("submit");
        submit.setPreferredSize(new Dimension(125, 40));
        submit.addActionListener(e -> {
            if (e.getSource() == submit) {
                System.out.println("[event]: submit button clicked");
            }

            try (Connection conn = JDBC.getConn()) {
                if (prefix.getSelectedItem() == null || forename.getText().isEmpty() ||
                        surname.getText().isEmpty() || telephone.getText().isEmpty() ||
                        date.getModel().getValue() == null || time.getSelectedItem() == null ||
                        occupants.getText().isEmpty() || tableNo.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(frame, "please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateBooking(conn);

                frame.dispose();
                Home home = new Home();
                home.start();

            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(125, 40));

        cancel.addActionListener(e -> {
            if (e.getSource() == cancel) {
                frame.dispose();

                try {
                    System.out.println("[event]: cancel button clicked");
                    ViewReservation view = new ViewReservation();
                    view.start();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        tableNo.addActionListener(e -> {
            if (e.getSource() == tableNo) {
                System.out.println("[event]: table selector clicked");

                if (date.getModel().getValue() == null) {
                    JOptionPane.showMessageDialog(frame, "please select a date first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SelectTable selectTable = new SelectTable();
                try {
                    java.util.Date selectedDate = (java.util.Date) date.getModel().getValue();
                    String selectedTime = (String) time.getSelectedItem();
                    boolean walkIn = isWalkIn.isSelected();

                    selectTable.start(selectedTables -> {

                        StringBuilder tables = new StringBuilder();
                        for (int i = 0; i < selectedTables.size(); i++) {
                            tables.append(selectedTables.get(i));
                            if (i < selectedTables.size() - 1) {
                                tables.append(", ");
                            }
                        }
                        tableNo.setText(tables.toString());

                    }, selectedDate, selectedTime, walkIn);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(submit);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateBooking(Connection conn) throws SQLException, IOException {
        try {
            boolean selectedIsWalkIn = isWalkIn.isSelected();
            String selectedPrefix = (String) prefix.getSelectedItem();
            String selectedForename = forename.getText();
            String selectedSurname = surname.getText();
            String selectedTelephone = telephone.getText();
            java.util.Date selectedDate = (java.util.Date) date.getModel().getValue();
            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
            String selectedTime = (String) time.getSelectedItem();
            int selectedOccupants = Integer.parseInt(occupants.getText());
            String selectedTableNo = tableNo.getText();

            String updateBookingSql = "UPDATE Bookings SET prefix=?, forename=?, surname=?, telephone=?, " +
                    "date=?, time=?, occupants=?, isWalkIn=? WHERE bookingID=?";
            PreparedStatement updateBookingStatement = conn.prepareStatement(updateBookingSql);

            updateBookingStatement.setString(1, selectedPrefix);
            updateBookingStatement.setString(2, selectedForename);
            updateBookingStatement.setString(3, selectedSurname);
            updateBookingStatement.setString(4, selectedTelephone);
            updateBookingStatement.setDate(5, sqlDate);
            updateBookingStatement.setString(6, selectedTime);
            updateBookingStatement.setInt(7, selectedOccupants);
            updateBookingStatement.setBoolean(8, selectedIsWalkIn);
            updateBookingStatement.setInt(9, bookingID);
            updateBookingStatement.executeUpdate();


            String deleteBookedTablesSql = "DELETE FROM BookedTables WHERE bookingID = ?";
            PreparedStatement deleteBookedTablesStatement = conn.prepareStatement(deleteBookedTablesSql);
            deleteBookedTablesStatement.setInt(1, bookingID);
            deleteBookedTablesStatement.executeUpdate();

            String[] tableNumbers = selectedTableNo.split(", ");
            String insertBookedTablesSql = "INSERT INTO BookedTables (bookingID, tableID, bookedTime) VALUES (?, ?, ?)";
            PreparedStatement insertBookedTablesStatement = conn.prepareStatement(insertBookedTablesSql);
            Timestamp bookingTimestamp = Timestamp.valueOf(sqlDate + " " + selectedTime + ":00"); // Combine date and time into a timestamp
            for (String tableNumber : tableNumbers) {
                int tableID = Integer.parseInt(tableNumber);
                insertBookedTablesStatement.setInt(1, bookingID);
                insertBookedTablesStatement.setInt(2, tableID);
                insertBookedTablesStatement.setTimestamp(3, bookingTimestamp);
                insertBookedTablesStatement.addBatch();
            }
            insertBookedTablesStatement.executeBatch();

        } finally {
            JDBC.closeConn(conn);
        }
    }
}
