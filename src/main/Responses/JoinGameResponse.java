package Responses;
/**
 * Represents a request to join a chess game. Contains a username and password.
 */
public class JoinGameResponse {
    /**
     * Potential error message for the response
     */
    private String message;
    /**
     * String authentication token for the response
     */
    private String authToken;
    /**
     * String representation of player color for the response
     */
    private String playerColor;
    /**
     * Integer for the response's gameID
     */
    private int gameID;
    private int status = 200;

    /**
     * Creates an instance of the JoinGameResponse class
     */
    public JoinGameResponse()  {

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

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
