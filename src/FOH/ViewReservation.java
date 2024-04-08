package FOH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setRadioButtons();

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

    public void setRadioButtons() {
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 40));
        radioPanel.setBackground(new Color(43, 51, 54));

        // Create radio buttons
        JRadioButton activeRadioButton = new JRadioButton("Active");
        JRadioButton weekRadioButton = new JRadioButton("Week");
        JRadioButton monthRadioButton = new JRadioButton("Month");

        activeRadioButton.setForeground(new Color(200, 200, 200));
        weekRadioButton.setForeground(new Color(200, 200, 200));
        monthRadioButton.setForeground(new Color(200, 200, 200));

        // Group radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(activeRadioButton);
        buttonGroup.add(weekRadioButton);
        buttonGroup.add(monthRadioButton);

        // Add radio buttons to the panel
        radioPanel.add(activeRadioButton);
        radioPanel.add(weekRadioButton);
        radioPanel.add(monthRadioButton);

        panel.add(radioPanel);
    }
}
