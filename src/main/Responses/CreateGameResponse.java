package Responses;


/**
 * Represents a response to a request to create a new game
 */
public class CreateGameResponse {
    /**
     * Integer for the response's gameID
     */
    private int gameID;
    /**
     * Authentication token for the response
     */
    private String authToken;
    /**
     * Potential error message for the response
     */
    private String message;
    private int status = 200;

    /**
     * Creates an instance of the CreateGameResponse class
     */
    public CreateGameResponse() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getAuthorization() {
        return authToken;
    }

    public void setAuthorization(String authorization) {
        this.authToken = authorization;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
