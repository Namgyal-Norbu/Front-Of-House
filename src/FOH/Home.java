package FOH;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;


/**
 * The Home class serves as the main entry point for the FOH Service Software,
 * providing a GUI home screen with options to navigate to different functionalities such as
 * creating reservations, viewing reservations, and serving tables.
 */
public class Home {
  private static JFrame frame;
  private static JPanel panel;

  /**
   * Constructs a new Home instance.
   * Initializes database connection and sets up the main frame and panel.
   *
   * @throws SQLException if an error occurs during database connection initialization.
   */
  public Home() throws SQLException {
    JDBC.startConn();

    frame = new JFrame();
    panel = new JPanel();
  }

  /**
   * Starts the home screen interface.
   *
   * @throws IOException if an I/O exception occurs during the interface setup.
   */
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

  /**
   * Loads and displays the logo on the home interface.
   * The logo is positioned within the panel based on specified bounds.
   */
  public void loadLogo() {
    ImageIcon icon = new ImageIcon("res/images/logo.png");
    JLabel logo = new JLabel(icon);
    logo.setBounds(175, -150, icon.getIconWidth(), icon.getIconHeight());
    panel.add(logo);
  }

  /**
   * Loads and displays the buttons for various functionalities on the home interface.
   */
  public void loadButtons() {
    JButton create = new JButton("Create Reservation");
    create.setBounds(75, 335, 200, 150);

    create.addActionListener(e -> {
      if (e.getSource() == create) {
        frame.dispose();
        CreateReservation createReservation;

        try {
          System.out.println("[event]: create button clicked");
          createReservation = new CreateReservation();
          createReservation.start();

        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });

    JButton view = new JButton("View Reservations");
    view.setBounds(375, 335, 200, 150);

    view.addActionListener(e -> {
      if (e.getSource() == view) {
        frame.dispose();
        ViewReservation viewReservation = new ViewReservation();
        try {
          System.out.println("[event]: view button clicked");
          viewReservation.start();

        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });

    JButton serve = new JButton("Serve Table");
    serve.setBounds(675, 335, 200, 150);

    serve.addActionListener(e -> {
      if (e.getSource() == serve) {
        frame.dispose();
        ServeSearch serveSearch = new ServeSearch();
        try {
          System.out.println("[event]: serve button clicked");
          serveSearch.start();

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
