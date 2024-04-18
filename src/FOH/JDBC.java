package FOH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The JDBC class manages the database connections for the FOH Service Software.
 * It provides methods to start, get, and close connections to the database.
 */
public class JDBC {
    private static Connection conn = null;

    /**
     * Constructs a new JDBC instance.
     * This constructor is provided to create objects of the JDBC class.
     */
    public JDBC() {}

    /**
     * Initializes the database connection using predefined credentials.
     * Attempts to establish a connection to the database and sets the conn static variable.
     *
     * @throws SQLException if there is a problem establishing the database connection.
     */
    public static void startConn() throws SQLException {
        try {
            String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t28";
            String username = "in2033t28_a";
            String password = "acGHaHnXjsA";

            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            closeConn(conn);
            throw new SQLException(e);
        }
    }

    /**
     * Returns the current database connection.
     * If no connection exists, this method will return null.
     *
     * @return The current database connection, or null if no connection is established.
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * Closes the specified database connection.
     * This method ensures the connection is closed to avoid resource leaks.
     *
     * @param conn The database connection to close.
     * @throws SQLException if there is an error closing the connection.
     */
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
