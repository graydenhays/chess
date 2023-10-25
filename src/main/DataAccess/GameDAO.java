package DataAccess;

import Models.GameModel;
import Models.User;
import dataAccess.DataAccessException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Data Access Object (DAO) class for managing games in the database.
 * This class provides methods for inserting, retrieving, updating, and finding GameModel objects.
 */
public class GameDAO {
    /**
     * A static HashMap class variable for storing games
     */
    public static HashMap<Integer, GameModel> gameList = new HashMap<>();

    /**
     * Creates an instance of the GameModel class
     *
     * @param game
     * @throws DataAccessException
     */
    void CreateGame(GameModel game) throws DataAccessException {

    }

    /**
     * Inserts new game into the database.
     *
     * @param game The GameModel object to insert.
     */
    public void Insert(GameModel game)  {

    }

    /**
     * Retrieves a specified game from the database by gameID
     *
     * @param gameID The unique identifier of the game to find.
     * @return The found GameModel object, or null if not found.
     */
    public GameModel Find(int gameID)   {
        return null;
    }

    /**
     * Retrieves all games from the database
     *
     * @return A collection of GameModel objects, or null if none are found.
     */
    public Collection<GameModel> FindAll()  {
        return null;
    }

    /**
     * Updates game in the database. Replaces the chessGame string that corresponds to a given gameID with a new chessGame string.
     *
     * @param gameID The unique identifier of the game to update.
     * @param game The game to be updated
     */
    void UpdateGame(int gameID, GameModel game) {

    }

    /**
     * Claims a spot in the game. Saves a player's username as a white or black player.
     *
     * @param username
     * @param game
     * @param playerColor
     */
    void ClaimSpot(String username, GameModel game, String playerColor) {

    }

    /**
     * Clears all Game data from the database
     */
    void Clear()    {

    }
}
