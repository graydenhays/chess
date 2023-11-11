package DataAccess;

import Models.AuthToken;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.Database;

import java.sql.*;

import java.util.UUID;

/**
 * Data Access Object (DAO) class for managing AuthToken objects in the database.
 * This class provides methods for inserting, retrieving, updating, and finding authentication tokens.
 */
public class AuthDAO {
    /**
     * A static HashMap class variable for storing authentication tokens
     */
    public static Database db = new Database();
    /**
     * Creates an instance of the AuthToken class
     *
     * @param username
     * @throws DataAccessException
     */
    public AuthToken CreateAuth(String username) throws SQLException, DataAccessException {
        Connection conn = db.getConnection();
        String authToken = UUID.randomUUID().toString();
        if (authToken.matches("[a-zA-Z0-9-]+") && username.matches("[a-zA-Z]+")) {
            var statement = "INSERT INTO authDAO (authToken, username) VALUES(?, ?)";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setString(1, authToken);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
            }
        }
        else {
            throw new DataAccessException("Error: Bad input");
        }
        db.returnConnection(conn);
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername(username);
        return auth;
    }

    /**
     * Removes an AuthToken from the database
     */
    public void Remove(String authToken) throws SQLException, DataAccessException {
        Connection conn = db.getConnection();

        if(Find(authToken) != null) {
            try (var preparedStatement = conn.prepareStatement("DELETE FROM authDAO WHERE authToken=?")) {
                preparedStatement.setString(1, authToken);
                preparedStatement.executeUpdate();
            }
        }
        else {
            throw new DataAccessException("Error: not authorized");
        }
        db.returnConnection(conn);
    }

    /**
     * Inserts new AuthToken into the database.
     *
     * @param auth The AuthToken object to insert.
     */
    public void Insert(AuthToken auth) throws SQLException, DataAccessException {
        Connection conn = db.getConnection();

        String authToken = auth.getAuthToken();
        String username = auth.getUsername();
        if (authToken.matches("[a-zA-Z0-9-]+") && username.matches("[a-zA-Z]+")) {
            var statement = "INSERT INTO authDAO (authToken, username) VALUES(?, ?)";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setString(1, authToken);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
            }
        }
        else {
            throw new DataAccessException("Error: Bad input");
        }

        db.returnConnection(conn);
    }

    /**
     * Retrieves a specified AuthToken from the database by username
     *
     * @param authToken The unique identifier of the AuthToken to find.
     * @return The found AuthToken object, or null if not found.
     */
    public AuthToken Find(String authToken) throws SQLException, DataAccessException {

        Connection conn = db.getConnection();

        AuthToken auth = new AuthToken();
        String u = null;
        String a = null;
        try (var preparedStatement = conn.prepareStatement("SELECT authToken, username FROM authDAO WHERE authToken=?")) {
            preparedStatement.setString(1, authToken);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    u = rs.getString("username");
                    a = rs.getString("authToken");
                }
            }
        }
        if(a == null)    {
            throw new DataAccessException("Error: Could not find authToken");
        }
        else {
            db.returnConnection(conn);
            auth.setUsername(new Gson().fromJson(u, String.class));
            auth.setAuthToken(new Gson().fromJson(a, String.class));
            return auth;
        }
    }

    /**
     * Clears all AuthToken data from the database
     */
    public void Clear() throws SQLException, DataAccessException {
        Connection conn = db.getConnection();

        try (var preparedStatement = conn.prepareStatement("TRUNCATE authDAO")) {
            preparedStatement.executeUpdate();
        }

        db.returnConnection(conn);
    }
}
