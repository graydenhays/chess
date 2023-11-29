package Models;
import chess.ChessGame;

import java.util.Objects;

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

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameModel gameModel = (GameModel) o;
        return gameID == gameModel.gameID && Objects.equals(whiteUsername, gameModel.whiteUsername) && Objects.equals(blackUsername, gameModel.blackUsername) && Objects.equals(gameName, gameModel.gameName) && Objects.equals(game, gameModel.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, whiteUsername, blackUsername, gameName, game);
    }
}
