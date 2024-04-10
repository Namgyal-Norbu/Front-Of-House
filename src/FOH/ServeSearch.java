package FOH;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServeSearch {

    private static JFrame frame;
    private static JPanel panel;

    private final JDatePickerImpl date;
    private final JComboBox<String> time;

    private final JLabel title;

    public ServeSearch() {
        frame = new JFrame();
        panel = new JPanel(new BorderLayout());
        title = new JLabel("Enter Booking Details");
        UtilDateModel model = new UtilDateModel();
        date = new JDatePickerImpl(new JDatePanelImpl(model));
        time = new JComboBox<>(new TimeModel());
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        search();
        setTitle();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(200, 200, 200));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 85, 0));
        panel.add(title, BorderLayout.NORTH);
    }

    public void search() {
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        datePanel.setBackground(new Color(43, 51, 54));
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(Color.WHITE);
        date.getComponent(0).setPreferredSize(new Dimension(200, 200)); // Example, adjust as necessary;
        datePanel.add(dateLabel);
        datePanel.add(date);
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setForeground(Color.WHITE);
        time.setPreferredSize(new Dimension(125, 100));
        datePanel.add(timeLabel);
        datePanel.add(time);


        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        inputPanel.setBackground(new Color(43, 51, 54));
        JTextField textField = new JTextField(20);
        JLabel tableID = new JLabel("Enter Table ID:");
        tableID.setForeground(Color.WHITE);
        inputPanel.add(tableID);
        inputPanel.add(textField);
        JButton search = new JButton("Submit");
        search.setPreferredSize(new Dimension(120, 40));
        search.addActionListener(e -> {
            String userInput = textField.getText();

            Date selectedDate = (Date) date.getModel().getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = selectedDate != null ? dateFormat.format(selectedDate) : "No date selected";
            String selectedTime = (String) time.getSelectedItem();
            String timeString = selectedTime != null ? selectedTime : "No time selected";
            try {
                System.out.println("[event]: Exit button clicked");
                ServeTable Servetable = new ServeTable();
                Servetable.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            System.out.println("Welcome " + userInput + ",Date: " + dateString + ", Time " + timeString);
        });
        inputPanel.add(search);


        JPanel dateTimeContainer = new JPanel();
        dateTimeContainer.add(datePanel);

        dateTimeContainer.setLayout(new BoxLayout(dateTimeContainer, BoxLayout.Y_AXIS));
        dateTimeContainer.add(inputPanel);


        JButton exit = new JButton("Exit");
        exit.setBounds(50, 380, 100, 50);
        exit.setPreferredSize(new Dimension(120, 40));
        inputPanel.add(exit);

        exit.addActionListener(e -> {
            frame.dispose();
            Home home = new Home();
            try {
                System.out.println("[event]: Exit button clicked");
                home.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(dateTimeContainer, BorderLayout.CENTER);
    }

}