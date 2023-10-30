package DataAccess;

import Models.AuthToken;
import dataAccess.DataAccessException;

import java.util.HashMap;
import java.util.UUID;

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
     * @param username
     * @throws DataAccessException
     */
    public AuthToken CreateAuth(String username) throws DataAccessException {
        AuthToken auth = new AuthToken();
        auth.setAuthToken(UUID.randomUUID().toString());
        auth.setUsername(username);
        authList.put(auth.getAuthToken(), auth);
        return auth;
    }

    /**
     * Removes an AuthToken from the database
     */
    public void Remove(String authToken) throws DataAccessException   {
        if(authList.containsKey(authToken)) {
            authList.remove(authToken);
        }
        else {
            throw new DataAccessException("Error: cannot find authentication token");
        }
    }

    /**
     * Inserts new AuthToken into the database.
     *
     * @param auth The AuthToken object to insert.
     */
    public void Insert(AuthToken auth) throws DataAccessException  {
        if(!authList.containsKey(auth.getAuthToken())) {
            authList.put(auth.getAuthToken(), auth);
        }
        else    {
            throw new DataAccessException("Error: auth token already exists");
        }
    }

    /**
     * Retrieves a specified AuthToken from the database by username
     *
     * @param authToken The unique identifier of the AuthToken to find.
     * @return The found AuthToken object, or null if not found.
     */
    public AuthToken Find(String authToken) throws DataAccessException   {
        if(authList.containsKey(authToken))  {
            return authList.get(authToken);
        }
        else {
            throw new DataAccessException("Error: token not found");
        }
    }

    /**
     * Clears all AuthToken data from the database
     */
    public void Clear() throws DataAccessException    {
        authList.clear();
    }
}
