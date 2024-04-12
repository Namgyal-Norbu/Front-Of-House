package FOH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewReservation {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel title;
    private final JTable reservations;

    public ViewReservation() {
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("View Reservations");
        reservations = new JTable();
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        setTitle();
        setPage();
        setExitButton();
        getBookings();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(950, 650);
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

    public void setPage() {
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 20));
        radioPanel.setBackground(new Color(43, 51, 54));

        JRadioButton active = new JRadioButton("Active");
        JRadioButton week = new JRadioButton("Week");
        JRadioButton month = new JRadioButton("Month");

        active.setForeground(new Color(200, 200, 200));
        week.setForeground(new Color(200, 200, 200));
        month.setForeground(new Color(200, 200, 200));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(active);
        buttonGroup.add(week);
        buttonGroup.add(month);

        radioPanel.add(active);
        radioPanel.add(week);
        radioPanel.add(month);

        setActionButtons(radioPanel);
        setBookingTable(radioPanel);
        panel.add(radioPanel, BorderLayout.CENTER);
    }

    public void setActionButtons(JPanel p) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 20));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton edit = new JButton("Edit Reservation");
        JButton delete = new JButton("Delete Reservation");
        JButton checkout = new JButton("Checkout");

        edit.addActionListener(e -> {
            if (e.getSource() == edit) {
                System.out.println("[event]: edit button clicked");
            }
        });

        delete.addActionListener(e -> {
            if (e.getSource() == delete) {
                int selectedRow = reservations.getSelectedRow();
                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this reservation?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        DefaultTableModel model = (DefaultTableModel) reservations.getModel();
                        int bookingID = (int) model.getValueAt(selectedRow, 0); // Assuming the reservation ID is in the first column

                        try {
                            deleteReservation(bookingID);
                            model.removeRow(selectedRow);

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(frame, "Failed to delete reservation from database.", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a reservation to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        checkout.addActionListener(e -> {
            if (e.getSource() == checkout) {
                System.out.println("[event]: checkout button clicked");
            }
        });

        buttonPanel.add(edit);
        buttonPanel.add(delete);
        buttonPanel.add(checkout);
        p.add(buttonPanel);
    }

    public void setBookingTable(JPanel p) {
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.setBackground(new Color(43, 51, 54));

        JScrollPane scrollPane = new JScrollPane(reservations);
        scrollPane.setPreferredSize(new Dimension(800, 350));

        String[] columns = {
                "ID",
                "Prefix",
                "Forename",
                "Surname",
                "Telephone",
                "Date",
                "Time",
                "Occupants",
                "Table No."
        };

        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columns);
        reservations.setModel(model);

        tablePanel.add(scrollPane);
        p.add(tablePanel);
    }

    public void getBookings() {
        DefaultTableModel model = (DefaultTableModel) reservations.getModel();
        model.setRowCount(0);

        try {
            Connection conn = JDBC.getConn();
            if (conn == null) {
                JDBC.startConn();
                conn = JDBC.getConn();
            }

            String sql = "SELECT b.bookingID, b.prefix, b.forename, b.surname, b.telephone, b.date, " +
                    "b.time, b.occupants, GROUP_CONCAT(bt.tableID ORDER BY bt.tableID SEPARATOR ', ') " +
                    "AS tables FROM Bookings b INNER JOIN BookedTables bt ON b.bookingID = bt.bookingID " +
                    "GROUP BY b.bookingID";

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int bookingID = rs.getInt("bookingID");
                String prefix = rs.getString("prefix");
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                String telephone = rs.getString("telephone");
                String date = rs.getString("date");
                String time = rs.getString("time");
                int occupants = rs.getInt("occupants");
                String tableNumbers = rs.getString("tables");

                // Add a row to the table model
                model.addRow(new Object[]{bookingID, prefix, forename, surname, telephone, date, time, occupants, tableNumbers});
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setExitButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton exit = new JButton("Exit");

        exit.addActionListener(e -> {
            if (e.getSource() == exit) {
                frame.dispose();
                Home home = null;
                try {
                    home = new Home();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    home.start();
                    System.out.println("[event]: exit button clicked");

                } catch (IOException ex) {
                    System.out.println("[error]: failed to call " + home + " start() method");
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.add(exit);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void deleteReservation(int bookingID) throws SQLException {
        Connection conn = null;
        try {
            conn = JDBC.getConn();
            conn.setAutoCommit(false); // Start a transaction

            String deleteBookedTablesSql = "DELETE FROM BookedTables WHERE bookingID IN " +
                    "(SELECT bookingID FROM Bookings WHERE bookingID = ?)";
            try (PreparedStatement deleteBookedTablesStatement = conn.prepareStatement(deleteBookedTablesSql)) {
                deleteBookedTablesStatement.setInt(1, bookingID);
                deleteBookedTablesStatement.executeUpdate();
            }

            String deleteBookingsSql = "DELETE FROM Bookings WHERE bookingID = ?";
            try (PreparedStatement deleteBookingsStatement = conn.prepareStatement(deleteBookingsSql)) {
                deleteBookingsStatement.setInt(1, bookingID);
                deleteBookingsStatement.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            JDBC.closeConn(conn);
        }
    }

}
