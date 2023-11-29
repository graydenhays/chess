package Requests;

/**
 * Represents a request to create a new game
 */
public class CreateGameRequest {
    /**
     * AuthToken for the CreateGame request
     */
    private String authorization;
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
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
