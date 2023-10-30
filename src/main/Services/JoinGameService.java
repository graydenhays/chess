package Services;

import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import Requests.JoinGameRequest;
import Responses.JoinGameResponse;
import dataAccess.DataAccessException;

/**
 * Implements the logic for an HTTP PUT method to add a specific color to the game
 */
public class JoinGameService {
    AuthDAO authDAO = new AuthDAO();
    GameDAO gameDAO = new GameDAO();

    /**
     * Receives a JoinGameRequest object as an input and returns the related JoinGameResponse object
     *
     * @param request of type JoinGameRequest
     * @return JoinGameResponse object
     */
    public JoinGameResponse joinGame(JoinGameRequest request)   {
        JoinGameResponse response = new JoinGameResponse();
        response.setPlayerColor(request.getPlayerColor());
        response.setAuthToken(request.getAuthToken());
        response.setGameID(request.getGameID());

        if(request.getGameID() < 1)  {
            response.setStatus(400);
            response.setMessage("Error: Bad request");
            return response;
        }

        try {
            gameDAO.Find(request.getGameID());
            authDAO.Find(request.getAuthToken());
        }
        catch (DataAccessException e)   {
            response.setStatus(401);
            return response;
        }

        String username = null;
        try {
            username = authDAO.Find(request.getAuthToken()).getUsername();
        }
        catch (DataAccessException e)   {
            response.setStatus(500);
            return response;
        }

        if(request.getPlayerColor() == null)    {
            return response;
        }

        try {
            gameDAO.ClaimSpot(username, gameDAO.Find(request.getGameID()), request.getPlayerColor());
        }
        catch (DataAccessException e)   {
            response.setStatus(403);
        }

        return response;
    }
}
