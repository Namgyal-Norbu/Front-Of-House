package FOH;

import java.awt.*;
import javax.swing.*;
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
    
    create.addActionListener(e -> {
      if(e.getSource()==create){
        frame.dispose();
        CreateReservation createReservation = new CreateReservation();
        try{
          System.out.println("[event]: create button clicked");
          createReservation.start();

        } catch(IOException ex){
          throw new RuntimeException(ex);
        }
      }
    });

    JButton view = new JButton("View Reservations");
    view.setBounds(375, 335, 200, 150);
    
    view.addActionListener(e -> {
      if (e.getSource() == view){
        frame.dispose();
        ViewReservation viewReservation = new ViewReservation();
        try{
          System.out.println("[event]: view button clicked");
          viewReservation.start();

        } catch(IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });

    JButton serve = new JButton("Serve Table");
    serve.setBounds(675, 335, 200, 150);
    
    serve.addActionListener(e -> {
      if (e.getSource() == serve){
        frame.dispose();
        ServeTable serveTable = new ServeTable();
        try {
          System.out.println("[event]: serve button clicked");
          serveTable.start();

        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });

    panel.add(create);
    panel.add(view);
    panel.add(serve);
  }
}
