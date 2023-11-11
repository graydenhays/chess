package DataAccess;

import Models.User;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.Database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) class for managing User in the database.
 * This class provides methods for inserting, retrieving, updating, and finding User objects.
 */
public class UserDAO    {
    /**
     * A static HashMap class variable for storing users
     */
    public static Database db = new Database();

    /**
     * Inserts new user into the database.
     *
     * @param user The User object to insert.
     */
    public void Insert(User user) throws SQLException, DataAccessException {
        Connection conn = db.getConnection();

        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        if (username.matches("[a-zA-Z]+") && password.matches("[a-zA-Z]+") && email.matches("[a-zA-Z.@]+")) {
            var statement = "INSERT INTO userDAO (username, password, email) VALUES(?, ?, ?)";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
            }
        }
        else {
            throw new DataAccessException("Error: bad input");
        }

        db.returnConnection(conn);
    }

    /**
     * Retrieves a specified user from the database by username
     *
     * @param username The unique identifier of the user to find.
     * @return The found User object, or null if not found.
     */
    public User Find(String username) throws SQLException, DataAccessException {

        Connection conn = db.getConnection();

        User user = new User();
        String u = null;
        String p = null;
        String e = null;
        try (var preparedStatement = conn.prepareStatement("SELECT username, password, email FROM userDAO WHERE username=?")) {
            preparedStatement.setString(1, username);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    u = rs.getString("username");
                    p = rs.getString("password");
                    e = rs.getString("email");
                }
            }
        }
        if(u == null)    {
            throw new DataAccessException("Error: Could not find user");
        }
        else {
            db.returnConnection(conn);
            user.setUsername(new Gson().fromJson(u, String.class));
            user.setPassword(new Gson().fromJson(p, String.class));
            user.setEmail(new Gson().fromJson(e, String.class));
            return user;
        }
    }

    /**
     * Clears all User data from the database
     */
    public void Clear() throws SQLException, DataAccessException {
        Connection conn = db.getConnection();

        try (var preparedStatement = conn.prepareStatement("TRUNCATE userDAO")) {
            preparedStatement.executeUpdate();
        }

        db.returnConnection(conn);
    }
}
