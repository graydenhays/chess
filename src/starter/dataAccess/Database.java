package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Database is responsible for creating connections to the database. Connections are
 * managed with a simple pool in order to increase performance. To obtain and
 * use connections represented by this class use the following pattern.
 *
 * <pre>
 *  public boolean example(String selectStatement, Database db) throws DataAccessException{
 *    var conn = db.getConnection();
 *    try (var preparedStatement = conn.prepareStatement(selectStatement)) {
 *        return preparedStatement.execute();
 *    } catch (SQLException ex) {
 *        throw new DataAccessException(ex.toString());
 *    } finally {
 *        db.returnConnection(conn);
 *    }
 *  }
 * </pre>
 */
public class Database {

    // FIXME: Change these fields, if necessary, to match your database configuration
    public static final String DB_NAME = "chess";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "#Isaiah40:31!";

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";

    private final LinkedList<Connection> connections = new LinkedList<>();

    /**
     * Get a connection to the database. This pulls a connection out of a simple
     * pool implementation. The connection must be returned to the pool after
     * you are done with it by calling {@link #returnConnection(Connection) returnConnection}.
     *
     * @return Connection
     */
    synchronized public Connection getConnection() throws DataAccessException {
        try {
            Connection connection;
            if (connections.isEmpty()) {
                connection = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
                connection.setCatalog(DB_NAME);
            } else {
                connection = connections.removeFirst();
            }
            return connection;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Return a previously acquired connection to the pool.
     *
     * @param connection previous obtained by calling {@link #getConnection() getConnection}.
     */
    synchronized public void returnConnection(Connection connection) {
        connections.add(connection);
    }

    public void configureDatabase() throws SQLException, DataAccessException {
        try (var conn = getConnection()) {
            var createDbStatement = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS chess");
            createDbStatement.executeUpdate();

            conn.setCatalog("chess");

            var createGameTable = """
            CREATE TABLE IF NOT EXISTS gameDAO (
                gameID INT NOT NULL AUTO_INCREMENT,
                whiteUsername VARCHAR(150),
                blackUsername VARCHAR(150),
                gameName VARCHAR(150) NOT NULL,
                game VARCHAR(1500) NOT NULL,
                PRIMARY KEY (gameID)
            )""";

            var createUserTable = """
            CREATE TABLE IF NOT EXISTS userDAO (
                username VARCHAR(150) NOT NULL,
                password VARCHAR(150) NOT NULL,
                email VARCHAR(150) NOT NULL,
                PRIMARY KEY (username)
            )""";

            var createAuthTable = """
            CREATE TABLE IF NOT EXISTS authDAO (
                authToken VARCHAR(150) NOT NULL,
                username VARCHAR(150) NOT NULL,
                PRIMARY KEY (authToken)
            )""";

            try (var createTableStatement = conn.prepareStatement(createGameTable)) {
                createTableStatement.executeUpdate();
            }
            try (var createTableStatement = conn.prepareStatement(createUserTable)) {
                createTableStatement.executeUpdate();
            }
            try (var createTableStatement = conn.prepareStatement(createAuthTable)) {
                createTableStatement.executeUpdate();
            }
        }
    }
}

