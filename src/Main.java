import java.io.IOException;
import java.sql.SQLException;

import FOH.Home;
import FOH.JDBC;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    System.out.println("booting up system...");
    System.out.println("[debug log]");
    try {
      JDBC.startConn();
      Home app = new Home();
      app.start();

    } catch (IOException e) {
        throw new IOException(e);

    } catch (SQLException e) {
        throw new SQLException(e);
    }
  }
}
