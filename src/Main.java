import ManagementToFOH.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import FOH.Home;


public class Main {
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
