package DataAccess;

import Models.AuthToken;
import Models.User;
import dataAccess.DataAccessException;

import java.util.HashMap;

/**
 * Data Access Object (DAO) class for managing AuthToken objects in the database.
 * This class provides methods for inserting, retrieving, updating, and finding authentication tokens.
 */
public class AuthDAO {
    /**
     * A static HashMap class variable for storing authentication tokens
     */
    public static HashMap<String, AuthToken> authList = new HashMap<>();

    /**
     * Creates an instance of the AuthToken class
     *
     * @param auth
     * @throws DataAccessException
     */
    void CreateAuth(AuthToken auth) throws DataAccessException {

    }

    /**
     * Removes a game from the database
     */
    public void Remove()   {

    }

    /**
     * Inserts new AuthToken into the database.
     *
     * @param auth The AuthToken object to insert.
     */
    public void Insert(AuthToken auth)  {

    }

    /**
     * Retrieves a specified AuthToken from the database by username
     *
     * @param username The unique identifier of the AuthToken to find.
     * @return The found AuthToken object, or null if not found.
     */
    public User Find(String username)   {
        return null;
    }

    /**
     * Clears all AuthToken data from the database
     */
    void Clear()    {

    }
}
