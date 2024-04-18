import java.io.IOException;
import java.sql.SQLException;
import FOH.Home;

/**
 * The Main class serves as the entry point for the application.
 * It initializes the system and starts the main functionality.
 */
public class Main {
  /**
   * The main method of the application.
   *
   * @param args the command-line arguments passed to the application
   * @throws IOException if an I/O exception occurs while accessing files, streams, or directories
   * @throws SQLException if an error occurs during database access
   */
  public static void main(String[] args) throws IOException, SQLException {
    System.out.println("booting up system...");
    System.out.println("[debug log]");
    try {
      Home app = new Home();
      app.start();

    } catch (IOException e) {
        throw new IOException(e);
    }
  }
}
