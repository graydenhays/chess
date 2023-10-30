package Services;

import DataAccess.AuthDAO;
import dataAccess.DataAccessException;

/**
 * Implements the logic for an HTTP DELETE method to log out the user represented by the authentication token
 */
public class LogoutService {
    AuthDAO authDAO = new AuthDAO();
    /**
     * Receives a LogoutRequest object as an input and returns the related LogoutResponse object
     *
     * @param authToken
     * @return LogoutResponse instance
     */
    public int logout(String authToken)   {
        try {
            authDAO.Remove(authToken);
        }
        catch(DataAccessException e)    {
            return 401;
        }
        return 200;
    }
}
