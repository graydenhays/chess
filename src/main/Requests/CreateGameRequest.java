package Requests;

import Models.AuthToken;

/**
 * Represents a request to create a new game
 */
public class CreateGameRequest {
    /**
     * AuthToken for the CreateGame request
     */
    private String authToken;
    /**
     * String representation for the gameName
     */
    private String gameName;

    /**
     * Creates an instance of the CreateGameRequest class
     */
    public CreateGameRequest()  {

    }

    public String getAuthorization() {
        return authToken;
    }

    public void setAuthorization(String authorization) {
        this.authToken = authorization;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
