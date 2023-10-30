package Services;

import DataAccess.AuthDAO;
import DataAccess.UserDAO;
import Models.User;
import Responses.RegisterResponse;
import dataAccess.DataAccessException;

/**
 * Implements the logic for an HTTP POST method to register a new user
 */
public class RegisterService {
    /**
     * Receives a RegisterRequest object as an input and returns the related RegisterResponse object
     *
     * @param request of type RegisterRequest
     * @return RegisterResponse object
     */
    private AuthDAO authDAO = new AuthDAO();
    private UserDAO userDAO = new UserDAO();
    public RegisterResponse register(User u)  {
        RegisterResponse response = new RegisterResponse();
        response.setUsername(u.getUsername());
        try {
            userDAO.Find(u.getUsername());
            response.setStatus(403);
        }
        catch (DataAccessException e)   {
            try {
                userDAO.CreateUser(u);
                response.setAuthToken(authDAO.CreateAuth(u.getUsername()).getAuthToken());
            }
            catch (DataAccessException ex)   {
                response.setStatus(500);
            }
        }

        return response;
    }
}
