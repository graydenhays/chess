package Services;

import Models.User;

/**
 * Represents a request to join a chess game. Contains a username and password.
 */
public class JoinGameRequest {
    private String playerColor;
    private int gameID;
    private String authToken;

    /**
     * Creates an instance of the JoinGameRequest class for a specific user
     * @param user
     */
    public JoinGameRequest(User user)    {

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

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
