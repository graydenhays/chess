package Services;

import Models.AuthToken;
import Models.GameModel;

/**
 * Represents a response to a request for a list of all games. Contains an array of games, an authentication token, and an error message.
 */
public class ListGamesResponse {
    private AuthToken authorization;
    private GameModel game;
    private String message;

    /**
     * Creates an instance of the ListGamesResponse class
     */
    public ListGamesResponse()  {

    }
    public AuthToken getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthToken authorization) {
        this.authorization = authorization;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
