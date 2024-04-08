package FOH;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ViewReservation {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel title;

    public ViewReservation() {
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("View Reservations");
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        setTitle();
        setActionButtons();
        setExitButton();

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

    public void setActionButtons() {
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 20));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton editButton = new JButton("Edit Reservation");
        JButton deleteButton = new JButton("Delete Reservation");
        JButton checkoutButton = new JButton("Checkout");

        editButton.addActionListener(e -> {

        });
        deleteButton.addActionListener(e -> {

        });
        checkoutButton.addActionListener(e -> {

        });

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(checkoutButton);

        radioPanel.add(buttonPanel);
        panel.add(radioPanel, BorderLayout.CENTER);
    }

    public void setExitButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton exit = new JButton("Exit");

        exit.addActionListener(e -> {
            if (e.getSource() == exit) {
                frame.dispose();
                Home home = new Home();
                try {
                    home.start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.add(exit);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

}
