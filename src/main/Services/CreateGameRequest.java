package Services;

import Models.AuthToken;

/**
 * Represents a request to create a new game
 */
public class CreateGameRequest {
    private AuthToken authorization;
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
