package DataAccess;

import Models.GameModel;

import java.util.Collection;
/**
 * Data Access Object (DAO) class for managing games in the database.
 * This class provides methods for inserting, retrieving, updating, and finding GameModel objects.
 */
public class GameDAO {
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
     */
    void UpdateGame(int gameID) {

    }
}
