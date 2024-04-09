package FOH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    private static Connection conn = null;

    public static void startConn() throws SQLException {
        try {
            String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t28";
            String username = "in2033t28_d";
            String password = "oExQzkCEgLw";

            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            if (conn != null) {
                closeConn(conn);
            }
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void closeConn(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
