package FOH;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Home {
  private static JFrame frame;
  private static JPanel panel;

  public Home() {
    frame = new JFrame();
    panel = new JPanel();
  }

  public void start() throws IOException {
    panel.setBackground(new Color(43, 51, 54));
    panel.setBorder(BorderFactory.createEmptyBorder());
    panel.setLayout(null);

    loadButtons();
    loadLogo();

    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("FOH Service Software");
    frame.setSize(950, 650);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);
  }

  public void loadLogo() {
    ImageIcon icon = new ImageIcon("res/images/logo.png");
    JLabel logo = new JLabel(icon);
    logo.setBounds(175, -150, icon.getIconWidth(), icon.getIconHeight());
    panel.add(logo);
  }

  public void loadButtons() {
    JButton create = new JButton("Create Reservation");
    create.setBounds(75, 335, 200, 150);
    create.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("create");
      }
    });

    JButton view = new JButton("View Reservations");
    view.setBounds(375, 335, 200, 150);
    view.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("view");
      }
    });

    JButton serve = new JButton("Serve Table");
    serve.setBounds(675, 335, 200, 150);
    serve.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("serve");
      }
    });

    panel.add(create);
    panel.add(view);
    panel.add(serve);
  }  
}
