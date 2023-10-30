package DataAccess;

import Models.GameModel;
import dataAccess.DataAccessException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

/**
 * Data Access Object (DAO) class for managing games in the database.
 * This class provides methods for inserting, retrieving, updating, and finding GameModel objects.
 */
public class GameDAO {
    /**
     * A static HashMap class variable for storing games
     */
    public static HashMap<Integer, GameModel> gameList = new HashMap<>();
    public static int gameIDNumber = 100;

    /**
     * Creates an instance of the GameModel class
     *
     * @param gameID
     * @throws DataAccessException
     */
    public void CreateGame(Integer gameID) throws DataAccessException {
        GameModel game = new GameModel();
        game.setGameID(gameID);

        if(gameList.containsKey(gameID)) {
            throw new DataAccessException("Error: description");
        }
        else {
            gameList.put(gameID, game);
        }
    }

    /**
     * Inserts new game into the database.
     *
     * @param game The GameModel object to insert.
     */
    public void Insert(GameModel game)  {
        gameList.put(game.gameID, game);
    }

    /**
     * Retrieves a specified game from the database by gameID
     *
     * @param gameID The unique identifier of the game to find.
     * @return The found GameModel object, or null if not found.
     */
    public GameModel Find(Integer gameID) throws DataAccessException  {
        if(gameList.containsKey(gameID))    {
            return gameList.get(gameID);
        }
        else {
            throw new DataAccessException("Error: game not found");
        }
    }

    /**
     * Retrieves all games from the database
     *
     * @return A collection of GameModel objects, or null if none are found.
     */
    public Collection<GameModel> FindAll() throws DataAccessException  {
        return gameList.values();
    }

    /**
     * Updates game in the database. Replaces the chessGame string that corresponds to a given gameID with a new chessGame string.
     *
     * @param gameID The unique identifier of the game to update.
     * @param game The game to be updated
     */
    public void UpdateGame(int gameID, GameModel game) {
        gameList.put(gameID, game);
    }

    /**
     * Claims a spot in the game. Saves a player's username as a white or black player.
     *
     * @param username
     * @param game
     * @param playerColor
     */
    public void ClaimSpot(String username, GameModel game, String playerColor) throws DataAccessException {
        if(Objects.equals(playerColor, "WHITE"))  {
            if(game.getWhiteUsername() == null) {
                game.whiteUsername = username;
            }
            else {
                throw new DataAccessException("Error: spot already taken");
            }
        }
        else if(Objects.equals(playerColor, "BLACK")){
            if(game.getBlackUsername() == null) {
                game.blackUsername = username;
            }
            else {
                throw new DataAccessException("Error: spot already taken");
            }
        }
        UpdateGame(game.getGameID(), game);
    }

    /**
     * Clears all Game data from the database
     */
    public void Clear() throws DataAccessException    {
        gameList.clear();
    }
}
