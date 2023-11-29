package Requests;


/**
 * Represents a request to join a chess game. Contains a username and password.
 */
public class JoinGameRequest {
    /**
     * String representation of player color
     */
    private String playerColor;
    /**
     * Integer for the request's gameID
     */
    private int gameID;
    /**
     * String for the request's authentication token
     */
    private String authorization;

    /**
     * Creates an instance of the JoinGameRequest class for a specific user
     */
    public JoinGameRequest()    {

    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getAuthToken() {
        return authorization;
    }

    public void setAuthToken(String authToken) {
        this.authorization = authToken;
    }
}
