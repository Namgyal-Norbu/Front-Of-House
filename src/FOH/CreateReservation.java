package FOH;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CreateReservation {
    private static JFrame frame;
    private static JPanel panel;

    public CreateReservation() {
        frame = new JFrame();
        panel = new JPanel();
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(null);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
