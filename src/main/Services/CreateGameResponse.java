package Services;

import Models.AuthToken;

/**
 * Represents a response to a request to create a new game
 */
public class CreateGameResponse {
    private int gameID;
    private AuthToken authorization;
    private String message;

    /**
     * Creates an instance of the CreateGameResponse class
     */
    public CreateGameResponse() {

    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public AuthToken getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthToken authorization) {
        this.authorization = authorization;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
