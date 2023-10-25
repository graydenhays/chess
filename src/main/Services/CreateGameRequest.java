package Services;

import Models.AuthToken;

/**
 * Represents a request to create a new game
 */
public class CreateGameRequest {
    /**
     * AuthToken for the CreateGame request
     */
    private AuthToken authorization;
    /**
     * String representation for the gameName
     */
    private String gameName;

    /**
     * Creates an instance of the CreateGameRequest class
     */
    public CreateGameRequest()  {

    }

    public AuthToken getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthToken authorization) {
        this.authorization = authorization;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
