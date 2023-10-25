package DataAccess;

import Models.User;
import dataAccess.DataAccessException;

import java.util.HashMap;

/**
 * Data Access Object (DAO) class for managing User in the database.
 * This class provides methods for inserting, retrieving, updating, and finding User objects.
 */
public class UserDAO    {
    /**
     * A static HashMap class variable for storing users
     */
    public static HashMap<String, User> userList = new HashMap<>();

    /**
     * Creates an instance of the User class
     *
     * @param u
     * @throws DataAccessException
     */
    void CreateUser(User u) throws DataAccessException {

    }

    /**
     * Inserts new user into the database.
     *
     * @param user The User object to insert.
     */
    public void Insert(User user)  {

    }

    /**
     * Retrieves a specified user from the database by username
     *
     * @param username The unique identifier of the user to find.
     * @return The found User object, or null if not found.
     */
    public User Find(String username)   {
        return null;
    }

    /**
     * Clears all User data from the database
     */
    void Clear()    {

    }

}
