package FOH;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class FirstCourse {

    private static JFrame frame;
    private static JPanel panel;
    private final JLabel tableInfo;
    private final JLabel customerName;
    private final JLabel time;

    private final Font commonFont = new Font("Arial", Font.BOLD, 36); // Example size
    private final Color fontColor = Color.WHITE;


    public FirstCourse() {
        frame = new JFrame();
        panel = new JPanel(new BorderLayout());
        tableInfo = new JLabel("Table ID: ");
        customerName = new JLabel("Customer Name: ");
        time = new JLabel("Time: ");
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
