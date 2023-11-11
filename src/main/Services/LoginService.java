package Services;

import DataAccess.AuthDAO;
import DataAccess.UserDAO;
import Models.User;
import Responses.LoginResponse;
import dataAccess.DataAccessException;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Implements the logic for an HTTP POST method to log in a specific user
 */
public class LoginService {
    AuthDAO authDAO = new AuthDAO();
    UserDAO userDAO = new UserDAO();

    /**
     * Receives a LoginRequest object as an input and returns the related LoginResponse object
     *
     * @param u of type LoginRequest
     * @return LoginResponse object
     */
    public LoginResponse login(User u) {
        LoginResponse response = new LoginResponse();
        response.setUsername(u.getUsername());
        try {
            User temp = userDAO.Find(u.username);
            if(!Objects.equals(temp.password, u.password)) {
                response.setStatus(401);
            }
        }
        catch (DataAccessException | SQLException e)   {
            response.setStatus(401);
        }
        try {
            response.setAuthToken(authDAO.CreateAuth(u.getUsername()).getAuthToken());
        }
        catch (DataAccessException | SQLException ex)   {
            response.setStatus(500);
        }
        return response;
    }
}
