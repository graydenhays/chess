package Models;
import chess.ChessGame;

/**
 * Creates a model of the game
 */
public class GameModel {
    /**
     * Integer representing identification number for GameModel instance
     */
    public int gameID;
    /**
     * String username for the white player in the game
     */
    public String whiteUsername;
    /**
     * String username for the black player in the game
     */
    public String blackUsername;
    /**
     * String name for the game
     */
    public String gameName;
    /**
     * ChessGame instance for the game
     */
    public ChessGame game;
}
