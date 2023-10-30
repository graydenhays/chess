package Services;

import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import Responses.ListGamesResponse;
import dataAccess.DataAccessException;

/**
 * Implements the logic for an HTTP GET method to get a list of all games
 */
public class ListGamesService {
    AuthDAO authDAO = new AuthDAO();
    GameDAO gameDAO = new GameDAO();
    /**
     * Receives a ListGamesRequest object as an input and returns the related ListGamesResponse object
     *
     * @param authToken
     * @return ListGamesResponse instance
     */
    public ListGamesResponse listGames(String authToken)   {
        ListGamesResponse response = new ListGamesResponse();
        response.setAuthorization(authToken);
        try {
            authDAO.Find(authToken);
        }
        catch (DataAccessException e)   {
            response.setStatus(401);
        }
        try {
            response.setGames(gameDAO.FindAll());
        }
        catch (DataAccessException e)   {
            response.setStatus(500);
        }
        return response;
    }
}
