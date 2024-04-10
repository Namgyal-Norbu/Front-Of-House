package FOH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    private static Connection conn = null;

    public static void startConn() throws SQLException {
        try {
            String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t28";
            String username = "in2033t28_a";
            String password = "acGHaHnXjsA";

            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            closeConn(conn);
            throw new SQLException(e);

        } finally {
            closeConn(conn);
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void closeConn(Connection conn) throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
