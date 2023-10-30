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
    public void CreateUser(User u) throws DataAccessException {
        if(userList.containsKey(u.username))    {
            throw new DataAccessException("Error: already taken");
        }
        else {
            userList.put(u.username, u);
        }
    }

    /**
     * Inserts new user into the database.
     *
     * @param user The User object to insert.
     */
    public void Insert(User user)  {
        userList.put(user.username, user);
    }

    /**
     * Retrieves a specified user from the database by username
     *
     * @param username The unique identifier of the user to find.
     * @return The found User object, or null if not found.
     */
    public User Find(String username) throws DataAccessException   {
        if(userList.containsKey(username))  {
            return userList.get(username);
        }
        else {
            throw new DataAccessException("Error: user not found");
        }
    }

    /**
     * Clears all User data from the database
     */
    public void Clear() throws DataAccessException    {
        userList.clear();
    }

}
