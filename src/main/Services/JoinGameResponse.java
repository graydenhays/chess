package Services;
/**
 * Represents a request to join a chess game. Contains a username and password.
 */
public class JoinGameResponse {
    private String message;
    private String authToken;
    private String playerColor;
    private int gameID;

    /**
     * Creates an instance of the JoinGameResponse class
     */
    JoinGameResponse()  {

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
