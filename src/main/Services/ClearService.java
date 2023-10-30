package Services;

import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import DataAccess.UserDAO;
import dataAccess.DataAccessException;

/**
 * Implements the logic for an HTTP DELETE method to clear the database
 */
public class ClearService {
    /**
     * Receives a ClearRequest object as an input and returns the related ClearResponse object
     *
     * @param request
     * @return ClearResponse instance
     */
    AuthDAO authDAO = new AuthDAO();
    GameDAO gameDAO = new GameDAO();
    UserDAO userDAO = new UserDAO();
    public int clear() {
        try {
            authDAO.Clear();
            gameDAO.Clear();
            userDAO.Clear();
        }
        catch(DataAccessException e)    {
            return 500;
        }
        return 200;
    }
}
