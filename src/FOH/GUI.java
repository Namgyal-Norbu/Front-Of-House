package FOH;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
  private static JFrame frame;
  private static JPanel panel;

  public static void app() throws IOException {
    frame = new JFrame();
    panel = new JPanel();

    JButton button = new JButton("click me");
    button.setBounds(375, 275, 200, 100);

    panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    panel.setLayout(null);
    panel.add(button);

    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("FOH Service Software");
    frame.setSize(950, 650);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
