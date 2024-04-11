package FOH;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.Date;

interface TableSelectionListener {
    void onTableSelected(List<Integer> selectedTables);
}

public class SelectTable {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel tablesLabel;
    private static List<Integer> selectedTables;
    private static TableSelectionListener selectionListener;

    public SelectTable() {
        frame = new JFrame();
        panel = new JPanel();
        tablesLabel = new JLabel("Selected Tables: ");
        tablesLabel.setForeground(Color.WHITE);
        selectedTables = new ArrayList<>();
    }

    public void start(TableSelectionListener listener, Date date, String time, boolean walkIn) throws SQLException {
        selectionListener = listener;
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        panel.setLayout(new BorderLayout());

        tableChoices(date, time, walkIn);
        panel.add(tablesLabel, BorderLayout.NORTH);
        setButton();


        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Select Tables");
        frame.setSize(550, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setButton() {
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        submitPanel.setBackground(new Color(43, 51, 54));
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, submitButton.getPreferredSize().height));
        submitButton.addActionListener(e -> {
            selectionListener.onTableSelected(selectedTables);
            frame.dispose();
        });
        submitPanel.add(submitButton);
        panel.add(submitPanel, BorderLayout.SOUTH);
    }

    public void tableChoices(Date selectedDate, String selectedTime, boolean walkIn) {
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 4, 35, 5));
        buttonsPanel.setBackground(new Color(43, 51, 54));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 15; i++) {
            JButton button = new JButton(String.valueOf(i));

            if (walkIn && (i == 13 || i == 14 || i == 15)) {
                button.setEnabled(true);

                if (isTableAvailable(i, selectedDate, selectedTime)) {
                    button.setEnabled(false);
                }

            } else if (!walkIn) {
                if (isTableAvailable(i, selectedDate, selectedTime)) {
                    button.setEnabled(false);
                }

            } else {
                button.setEnabled(false);
            }


            button.addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                int tableNumber = Integer.parseInt(clickedButton.getText());

                if (selectedTables.contains(tableNumber)) {
                    selectedTables.remove(Integer.valueOf(tableNumber));

                } else {
                    selectedTables.add(tableNumber);
                }
                updateSelectedTablesLabel();
            });
            buttonsPanel.add(button);
        }

        panel.add(buttonsPanel, BorderLayout.CENTER);
    }


    private void updateSelectedTablesLabel() {
        StringBuilder selectedTablesText = new StringBuilder("Selected Tables: ");
        for (Integer table : selectedTables) {
            selectedTablesText.append(String.format("%02d", table)).append(", ");
        }
        if (!selectedTables.isEmpty()) {
            selectedTablesText.setLength(selectedTablesText.length() - 2);
        }
        tablesLabel.setText(selectedTablesText.toString());
    }

    public boolean isTableAvailable(int tableNumber, Date selectedDate, String selectedTime) {

        // Concatenate date and time
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
        String dateTimeString = dateString + " " + selectedTime;

        try {
            // Parse the concatenated string to a java.util.Date object
            Date selectedDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTimeString);
            // Convert the java.util.Date object to a java.sql.Timestamp object
            Timestamp selectedTimestamp = new Timestamp(selectedDateTime.getTime());

            // Calculate booking end time
            Calendar cal = Calendar.getInstance();
            cal.setTime(selectedTimestamp);
            cal.add(Calendar.HOUR_OF_DAY, 3);
            Timestamp bookingEndTime = new Timestamp(cal.getTimeInMillis());

            // Execute SQL query to check for overlapping time intervals
            String sql = "SELECT COUNT(*) FROM BookedTables " +
                    "WHERE tableID = ? AND bookedTime < ? AND ? < DATE_ADD(bookedTime, INTERVAL 3 HOUR)";

            try {
                Connection conn = JDBC.getConn();
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setInt(1, tableNumber);
                statement.setTimestamp(2, bookingEndTime);
                statement.setTimestamp(3, selectedTimestamp);



                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count != 0;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

}