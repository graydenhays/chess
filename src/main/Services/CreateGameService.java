package Services;

import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import Models.GameModel;
import dataAccess.DataAccessException;

import java.sql.SQLException;

/**
 * Implements the logic for an HTTP POST method to create a new game
 */
public class CreateGameService {
    private AuthDAO authDAO = new AuthDAO();
    private GameDAO gameDAO = new GameDAO();
    /**
     * Receives a CreateGameRequest object as an input and returns the related CreateGameResponse object
     *
     * @param game
     * @param authToken
     * @return int
     */

    public int createGame(GameModel game, String authToken) {
        /*
        GameDAO.gameIDNumber += 1;
        game.setGameID(GameDAO.gameIDNumber);
         */

        try {
            authDAO.Find(authToken);
        }
        catch (DataAccessException | SQLException e)   {
            return 401;
        }

        try {
            game.setGameID(gameDAO.Insert(game));
        }
        catch (DataAccessException | SQLException e)   {
            return 500;
        }
        return 200;
    }
}
